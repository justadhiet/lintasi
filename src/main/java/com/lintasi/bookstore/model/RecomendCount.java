package com.lintasi.bookstore.model;

public class RecomendCount {

	private Book book;
	private long count;
	
	public RecomendCount(Book book, long count) {
		this.book = book;
		this.count = count;
	}

	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}


	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	
	
}
