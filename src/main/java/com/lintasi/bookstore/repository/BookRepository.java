package com.lintasi.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lintasi.bookstore.model.BookModel;

public interface BookRepository extends JpaRepository<BookModel, Integer>{

}
