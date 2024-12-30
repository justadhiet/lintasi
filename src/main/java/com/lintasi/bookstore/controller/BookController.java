package com.lintasi.bookstore.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lintasi.bookstore.exception.MyFileNotFoundException;
import com.lintasi.bookstore.model.Book;
import com.lintasi.bookstore.model.DashboardCount;
import com.lintasi.bookstore.model.RecomendCount;
import com.lintasi.bookstore.payload.response.BookResponse;
import com.lintasi.bookstore.payload.response.CommonResponse;
import com.lintasi.bookstore.payload.response.MessageResponse;
import com.lintasi.bookstore.payload.response.UploadFileResponse;
import com.lintasi.bookstore.service.BookService;
import com.lintasi.bookstore.service.FileStorageService;
import com.lintasi.bookstore.service.PricingService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	BookService bookService;
	@Autowired
	PricingService pricingService;
	@Autowired
	FileStorageService fileStorageService;
	
	@GetMapping("/dash")
	public ResponseEntity<CommonResponse> list(){
		List<BookResponse> result = new ArrayList<BookResponse>();
		for (Book model : bookService.listAllBook()) {
			result.add(getResponseFromModel(model));
		}
		
		CommonResponse r = new CommonResponse("ok", "ok", result);
		return new ResponseEntity<CommonResponse>(r, HttpStatus.OK);
	}
	
	@GetMapping("/dash/count")
	public ResponseEntity<CommonResponse> listCount(){
		DashboardCount result = new DashboardCount();
		result.setCountBooks(bookService.countBooks());
		result.setCountUsers(bookService.countUsers());
		result.setCountGenres(bookService.countGenres());

		CommonResponse r = new CommonResponse("ok", "ok", result);
		return new ResponseEntity<CommonResponse>(r, HttpStatus.OK);
	}
	
	@GetMapping("/dash/recomend")
	public ResponseEntity<CommonResponse> listRecomend(){
		CommonResponse r;
		try {
			List<RecomendCount> result = bookService.listAllBookRecomend();
			r = new CommonResponse("ok", "ok", result);
		} catch (Exception e) {
			r = new CommonResponse("error", e.getMessage(), null);
		}
		return new ResponseEntity<CommonResponse>(r, HttpStatus.OK);
	}
	
	@GetMapping("/dash/recomendMonth/{id}")
	public ResponseEntity<CommonResponse> listRecomendByMonth(@PathVariable String id) {
		CommonResponse r;
		try {
			String date = "01/" + id.substring(4, 6) + "/" + id.substring(0, 4);
			LocalDate now =  LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
			Date startDate = Date.from(now.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date endDate = Date.from(lastDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
			List<RecomendCount> result = bookService.listBookRecomendByMonth(startDate, endDate);
			r = new CommonResponse("ok", "ok", result);
		} catch (Exception e) {
			r = new CommonResponse("error", e.getMessage(), null);
		}
		return new ResponseEntity<CommonResponse>(r, HttpStatus.OK);
	}
	
	@GetMapping("/dash/genre/{id}")
	public ResponseEntity<CommonResponse> listBookByGenre(@PathVariable Integer id){
		CommonResponse r;
		try {
			List<Book> result = bookService.listBookByGenre(id);
			r = new CommonResponse("ok", "ok", result);
		} catch (Exception e) {
			r = new CommonResponse("error", e.getMessage(), null);
		}
		return new ResponseEntity<CommonResponse>(r, HttpStatus.OK);
	}
	
	@GetMapping("/dash/{id}")
	public ResponseEntity<CommonResponse> get(@PathVariable Integer id){
		CommonResponse r;
		try {
			BookResponse result = getResponseFromModel(bookService.getBook(id));
			r = new CommonResponse("ok", "ok", result);
		} catch (Exception e) {
			r = new CommonResponse("error", e.getMessage(), null);
		}
		return new ResponseEntity<CommonResponse>(r, HttpStatus.OK);
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
	public ResponseEntity<CommonResponse> add(@RequestBody Book book) {
		CommonResponse r;
		try {
			bookService.saveBook(book);
			r = new CommonResponse("ok", "ok", null);
		} catch (Exception e) {
			r = new CommonResponse("error", e.getMessage(), null);
		}
		return new ResponseEntity<CommonResponse>(r, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse>  delete(@PathVariable Integer id) {
		try {
			bookService.deleteBook(bookService.getBook(id));
			return ResponseEntity.ok(new MessageResponse("Book deleted successfully!"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Book can't be delete! e:" + e.getMessage()));
		}
	}
	
	@PostMapping("/uploadImage/{id}")
	@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN')")
	public ResponseEntity<UploadFileResponse> upload(@PathVariable Integer id, @RequestParam("image") MultipartFile file) {
		try {
			Book book = bookService.getBook(id);
			if (book != null) {
				fileStorageService.setType("cover");
				String fileName = fileStorageService.storeFile(file);

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/files/downloadFile/cover/").path(fileName).toUriString();
				book.setPicture(fileDownloadUri);
				bookService.saveBook(book);
				
				UploadFileResponse res = new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(),
						file.getSize());
				return new ResponseEntity<UploadFileResponse>(res, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<UploadFileResponse>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UploadFileResponse>(HttpStatus.NOT_FOUND);
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
