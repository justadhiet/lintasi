package com.lintasi.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lintasi.bookstore.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query("SELECT COUNT(u) FROM Recomended u WHERE u.bookId=?1")
	long countRecommended(Integer bookId);
	
	@Query("SELECT COUNT(u) FROM Favorite u WHERE u.bookId=?1")
	long countFavorite(Integer bookId);
	
}
