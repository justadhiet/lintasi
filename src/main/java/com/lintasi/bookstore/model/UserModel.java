package com.lintasi.bookstore.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	private String username;
	private String name;
	private String email;
	private String noHp;
	private String address;
	private Timestamp joinDate;
	
	@ManyToOne
	@JoinColumn(name = "id_role")
	private RoleModel idRole;
	
	public UserModel() {
		
	}

	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNoHp() {
		return noHp;
	}


	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Timestamp getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}


	public RoleModel getIdRole() {
		return idRole;
	}


	public void setIdRole(RoleModel role) {
		this.idRole = role;
	}
	
	
	
	
}
