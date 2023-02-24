package com.lintasi.bookstore.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lintasi.bookstore.model.Book;
import com.lintasi.bookstore.model.RecomendCount;
import com.lintasi.bookstore.payload.response.BookResponse;
import com.lintasi.bookstore.payload.response.MessageResponse;
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
	
	@GetMapping("/dash")
	public List<BookResponse> list(){
		List<BookResponse> result = new ArrayList<BookResponse>();
		for (Book model : bookService.listAllBook()) {
			result.add(getResponseFromModel(model));
		}
		return result;
	}
	
	@GetMapping("/dash/recomend")
	public List<RecomendCount> listRecomend(){
		return bookService.listAllBookRecomend();
	}
	
	@GetMapping("/dash/recomendMonth/{id}")
	public List<RecomendCount> listRecomendByMonth(@PathVariable String id) {
		try {
			String date = "01/" + id.substring(4, 6) + "/" + id.substring(0, 4);
			LocalDate now =  LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
			Date startDate = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date endDate = Date.from(lastDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
			return bookService.listBookRecomendByMonth(startDate, endDate);
		} catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/dash/genre/{id}")
	public List<Book> listBookByGenre(@PathVariable Integer id){
		return bookService.listBookByGenre(id);
	}
	
	@GetMapping("/dash/{id}")
	public ResponseEntity<BookResponse> get(@PathVariable Integer id){
		try {
			BookResponse book = getResponseFromModel(bookService.getBook(id));
			return new ResponseEntity<BookResponse>(book, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<BookResponse>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
	public ResponseEntity<?> add(@RequestBody Book book) {
		
		try {
			bookService.saveBook(book);
			return ResponseEntity.ok(new MessageResponse("Book inserted successfully!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Book can't be inserted! e:" + e.getMessage()));
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			bookService.deleteBook(bookService.getBook(id));
			return ResponseEntity.ok(new MessageResponse("Book deleted successfully!"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Book can't be delete! e:" + e.getMessage()));
		}
	}
	
	private BookResponse getResponseFromModel(Book model) {
		BookResponse response = new BookResponse();
		response.setBookModel(model);
		response.setFavorite(bookService.countFavoriteBook(model.getBookId()));
		response.setRecomended(bookService.countRecomendedBook(model.getBookId()));
		response.setRate(bookService.countRateBook(model.getBookId()));
		response.setPricing(pricingService.getPricingActive(model.getBookId()));
		return response;
	}
}
