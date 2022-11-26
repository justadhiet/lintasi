package com.lintasi.bookstore.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class BookModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookId;
	private byte[] picture;
	private String title;
	private String isbn;
	private String description;
	private String publisher;
	private String author;
	private String printLength;
	private String dimension;
	private String language;
	private Timestamp publicationDate;
	
	@OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
	private PricingModel pricing;
	@OneToMany(cascade = CascadeType.ALL)
	private List<RecomendedModel> recomend;
	@OneToMany(cascade = CascadeType.ALL)
	private List<FavoriteModel> favorite;

	public BookModel() {
		
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrintLength() {
		return printLength;
	}

	public void setPrintLength(String printLength) {
		this.printLength = printLength;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Timestamp getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Timestamp publicationDate) {
		this.publicationDate = publicationDate;
	}

	public PricingModel getPricing() {
		return pricing;
	}

	public void setPricing(PricingModel pricing) {
		this.pricing = pricing;
	}
	
	
	
}
