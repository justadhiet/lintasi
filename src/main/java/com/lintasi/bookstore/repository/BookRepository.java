package com.lintasi.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lintasi.bookstore.model.BookModel;

public interface BookRepository extends JpaRepository<BookModel, Integer>{

	@Query("SELECT COUNT(u) FROM RecomendedModel u WHERE u.bookId=?1")
	long countRecommended(Integer bookId);
	
	@Query("SELECT COUNT(u) FROM FavoriteModel u WHERE u.bookId=?1")
	long countFavorite(Integer bookId);
	
}
