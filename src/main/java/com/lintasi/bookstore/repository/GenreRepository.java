package com.lintasi.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lintasi.bookstore.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>{

	List<Genre> findByGenreId(Integer id);
}
