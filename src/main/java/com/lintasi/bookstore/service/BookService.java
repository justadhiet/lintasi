package com.lintasi.bookstore.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintasi.bookstore.model.Book;
import com.lintasi.bookstore.model.RecomendCount;
import com.lintasi.bookstore.repository.BookRepository;

@Service
@Transactional
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public List<Book> listAllBook(){
		return bookRepository.findAll();
	}
	
	public List<RecomendCount> listAllBookRecomend(){
		return bookRepository.recomendList();
	}
	
	public List<RecomendCount> listBookRecomendByMonth(Date startDate, Date endDate){
		return bookRepository.recomendListByMonth(startDate, endDate);
	}
	
	public List<Book> listBookByGenre(Integer id){
		return bookRepository.findByGenreId(id);
	}
	
	public void saveBook(Book book) {
		bookRepository.save(book);
	}
	
	public Book getBook(Integer id) {
		return bookRepository.findById(id).get();
	}
	
	public void deleteBook(Book book) {
		bookRepository.delete(book);
	}
	
	public long countRecomendedBook(Integer id) {
		return bookRepository.countRecommended(id);
	}
	
	public long countFavoriteBook(Integer id) {
		return bookRepository.countFavorite(id);
	}
}
