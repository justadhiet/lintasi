package com.lintasi.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lintasi.bookstore.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	List<Review> findByUserId(int userId);
	List<Review> findByBookId(int bookId);
	List<Review> findByBookIdAndUserId(int bookId, int userId);
	
}
