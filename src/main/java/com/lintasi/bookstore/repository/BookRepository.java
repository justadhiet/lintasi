package com.lintasi.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lintasi.bookstore.model.Book;
import com.lintasi.bookstore.model.RecomendCount;

public interface BookRepository extends JpaRepository<Book, Integer>{

	@Query("SELECT COUNT(u) FROM Recomended u WHERE u.bookId=?1")
	long countRecommended(Integer bookId);
	
	@Query("SELECT COUNT(u) FROM Favorite u WHERE u.bookId=?1")
	long countFavorite(Integer bookId);
	
	@Query("SELECT new com.lintasi.bookstore.model.RecomendCount(b, count(*) as count) "
			+ " FROM Recomended a inner join Book b on a.bookId = b.bookId GROUP BY a.bookId")
	List<RecomendCount> recomendList();
	
	List<Book> findByGenreId(Integer genreId);
	
}
