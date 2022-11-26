package com.lintasi.bookstore.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "pricing")
public class PricingModel {
	
	@Id
	@Column(name = "book_id")
	private int bookId;
	private long price;
	private String status;
	private long discount;
	private Timestamp timeRange;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private BookModel book;
	
	public PricingModel() {
		
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getDiscount() {
		return discount;
	}

	public void setDiscount(long discount) {
		this.discount = discount;
	}

	public Timestamp getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(Timestamp timeRange) {
		this.timeRange = timeRange;
	}
	
}
