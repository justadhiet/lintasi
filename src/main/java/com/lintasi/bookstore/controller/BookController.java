package com.lintasi.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lintasi.bookstore.model.BookModel;
import com.lintasi.bookstore.response.BookResponse;
import com.lintasi.bookstore.service.BookService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;
	
	@GetMapping("")
	public List<BookResponse> list(){
		List<BookResponse> result = new ArrayList<BookResponse>();
		for (BookModel model : bookService.listAllBook()) {
			result.add(getResponseFromModel(model));
		};
		return result;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BookModel> get(@PathVariable Integer id){
		try {
			BookModel book = bookService.getBook(id);
			return new ResponseEntity<BookModel>(book, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<BookModel>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("")
	public void add(@RequestBody BookModel book) {
		bookService.saveBook(book);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		bookService.deleteBook(id);
	}
	
	private BookResponse getResponseFromModel(BookModel model) {
		BookResponse response = new BookResponse();
		response.setBookModel(model);
		response.setFavorite(bookService.countFavoriteBook(model.getBookId()));
		response.setRecomended(bookService.countRecomendedBook(model.getBookId()));
		return response;
	}
}
