package com.lintasi.bookstore.controller;

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

import com.lintasi.bookstore.model.Review;
import com.lintasi.bookstore.payload.response.MessageResponse;
import com.lintasi.bookstore.service.ReviewService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/review")
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@GetMapping("/user/{id}")
	public List<Review> reviewByUser(@PathVariable Integer id){
		return reviewService.getReviewByUser(id);
	}
	
	@GetMapping("/book/{id}")
	public List<Review> reviewByBook(@PathVariable Integer id){
		return reviewService.getReviewByBook(id);
	}
	
	@GetMapping("/{bookId}/{userId}")
	public ResponseEntity<Review> get(@PathVariable Integer bookId, @PathVariable Integer userId){
		try {
			Review review = reviewService.getReviewByBookAndUser(bookId, userId);
			return new ResponseEntity<Review>(review, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> add(@RequestBody Review review) {
		try {
			reviewService.saveReview(review);
			return ResponseEntity.ok(new MessageResponse("Review inserted successfully!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Review can't be update! e:" + e.getMessage()));
		}
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('EDITOR') or hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			reviewService.deleteReview(id);
			return ResponseEntity.ok(new MessageResponse("Review deleted successfully!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Review can't be delete! e:" + e.getMessage()));
		}
	}
}
