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

import com.lintasi.bookstore.model.Book;
import com.lintasi.bookstore.payload.response.BookResponse;
import com.lintasi.bookstore.service.BookService;
import com.lintasi.bookstore.service.PricingService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	BookService bookService;
	@Autowired
	PricingService pricingService;
	
	@GetMapping("")
	public List<BookResponse> list(){
		List<BookResponse> result = new ArrayList<BookResponse>();
		for (Book model : bookService.listAllBook()) {
			result.add(getResponseFromModel(model));
		};
		return result;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> get(@PathVariable Integer id){
		try {
			Book book = bookService.getBook(id);
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("")
	public void add(@RequestBody Book book) {
		bookService.saveBook(book);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		bookService.deleteBook(id);
	}
	
	private BookResponse getResponseFromModel(Book model) {
		BookResponse response = new BookResponse();
		response.setBookModel(model);
		response.setFavorite(bookService.countFavoriteBook(model.getBookId()));
		response.setRecomended(bookService.countRecomendedBook(model.getBookId()));
		response.setPricing(pricingService.getPricingByBook(model.getBookId()));
		return response;
	}
}
