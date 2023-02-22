package com.lintasi.bookstore.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "recomended")
@IdClass(RecomendedId.class)
public class Recomended implements Serializable{

	@Id
	@Column(name = "book_id")
	private int bookId;
	@Id
	@Column(name = "user_id")
	private int userId;
	private Date recomendTime;
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getRecomendTime() {
		return recomendTime;
	}
	public void setRecomendTime(Date recomendTime) {
		this.recomendTime = recomendTime;
	}
	
	
}
