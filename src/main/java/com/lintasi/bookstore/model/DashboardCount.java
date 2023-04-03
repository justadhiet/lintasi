/**
 * 
 */
package com.lintasi.bookstore.model;

/**
 * @author wicaka
 *
 */
public class DashboardCount {

	private long countBooks;
	private long countGenres;
	private long countUsers;
	
	public DashboardCount() {
		
	}
	
	public long getCountBooks() {
		return countBooks;
	}
	public void setCountBooks(long countBooks) {
		this.countBooks = countBooks;
	}
	public long getCountGenres() {
		return countGenres;
	}
	public void setCountGenres(long countGenres) {
		this.countGenres = countGenres;
	}
	public long getCountUsers() {
		return countUsers;
	}
	public void setCountUsers(long countUsers) {
		this.countUsers = countUsers;
	}
	
	
}
