package com.lintasi.bookstore.model;

import java.io.Serializable;
import java.util.Objects;

public class PricingId implements Serializable {
	
	private int bookId;
	private long price;

	public PricingId() {
		
	}
	
	public PricingId(int bookid, long price) {
		this.bookId = bookid;
		this.price = price;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricingId pricingId = (PricingId) o;
        return bookId == pricingId.bookId && price == pricingId.price;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bookId,price);
	}
}
