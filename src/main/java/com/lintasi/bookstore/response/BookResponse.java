package com.lintasi.bookstore.response;

import com.lintasi.bookstore.model.BookModel;

public class BookResponse {

	private BookModel bookModel;
	private long recomended;
	private long favorite;
	
	public BookModel getBookModel() {
		return bookModel;
	}
	public void setBookModel(BookModel bookModel) {
		this.bookModel = bookModel;
	}
	public long getRecomended() {
		return recomended;
	}
	public void setRecomended(long recomended) {
		this.recomended = recomended;
	}
	public long getFavorite() {
		return favorite;
	}
	public void setFavorite(long favorite) {
		this.favorite = favorite;
	}
	
	
	
}
