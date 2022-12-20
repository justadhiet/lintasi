package com.lintasi.bookstore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRole;
	@Enumerated(EnumType.STRING)
	private ERole nama;
	private String akses;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<User> user;
	
	public Role() {
		
	}

	public int getIdRole() {
		return idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public ERole getNama() {
		return nama;
	}

	public void setNama(ERole nama) {
		this.nama = nama;
	}

	public String getAkses() {
		return akses;
	}

	public void setAkses(String akses) {
		this.akses = akses;
	}
	
}
