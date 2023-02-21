package com.lintasi.bookstore.model;

import java.io.Serializable;
import java.util.Objects;

public class RecomendedId implements Serializable{
	
	private int bookId;
	private int userId;
	
	public RecomendedId() {
		
	}

	public RecomendedId(int bookid, int userId) {
		this.bookId = bookid;
		this.userId = userId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecomendedId recomendedId = (RecomendedId) o;
        return bookId == recomendedId.bookId && userId == recomendedId.userId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bookId,userId);
	}
}
