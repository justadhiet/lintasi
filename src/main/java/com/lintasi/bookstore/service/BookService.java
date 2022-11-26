package com.lintasi.bookstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintasi.bookstore.model.BookModel;
import com.lintasi.bookstore.repository.BookRepository;

@Service
@Transactional
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public List<BookModel> listAllBook(){
		return bookRepository.findAll();
	}
	
	public void saveBook(BookModel book) {
		bookRepository.save(book);
	}
	
	public BookModel getBook(Integer id) {
		return bookRepository.findById(id).get();
	}
	
	public void deleteBook(Integer id) {
		bookRepository.deleteById(id);
	}
	
	public long countRecomendedBook(Integer id) {
		return bookRepository.countRecommended(id);
	}
	
	public long countFavoriteBook(Integer id) {
		return bookRepository.countFavorite(id);
	}
}
