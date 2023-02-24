package com.lintasi.bookstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintasi.bookstore.model.Review;
import com.lintasi.bookstore.repository.ReviewRepository;

@Service
@Transactional
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public List<Review> getReviewByBook(int bookId){
		return reviewRepository.findByBookId(bookId);
	}
	
	public List<Review> getReviewByUser(int userId){
		return reviewRepository.findByUserId(userId);
	}
	
	public Review getReviewByBookAndUser(int bookId, int userId){
		List<Review> res = reviewRepository.findByBookIdAndUserId(bookId, userId);
		if(res.size()>0) {
			return res.get(0);
		}
		return null;
	}
	
	public void saveReview(Review review) {
		reviewRepository.save(review);
	}
	
	public void deleteReview(Integer id) {
		reviewRepository.deleteById(id);
	}

}
