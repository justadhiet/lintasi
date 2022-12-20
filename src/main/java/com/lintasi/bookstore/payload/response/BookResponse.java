package com.lintasi.bookstore.payload.response;

import java.util.List;

import com.lintasi.bookstore.model.Book;
import com.lintasi.bookstore.model.Pricing;

public class BookResponse {

	private Book book;
	private long recomended;
	private long favorite;
	private List<Pricing> pricing;
	
	public Book getBookModel() {
		return book;
	}
	public void setBookModel(Book book) {
		this.book = book;
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
	public List<Pricing> getPricing() {
		return pricing;
	}
	public void setPricing(List<Pricing> pricing) {
		this.pricing = pricing;
	}	
	
}
