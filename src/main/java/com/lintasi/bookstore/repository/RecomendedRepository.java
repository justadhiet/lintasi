package com.lintasi.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lintasi.bookstore.model.Recomended;
import com.lintasi.bookstore.model.RecomendedId;

public interface RecomendedRepository extends JpaRepository<Recomended, RecomendedId> {
	
	List<Recomended> findByBookId(Integer bookid);
	List<Recomended> findByUserId(Integer userId);
}
