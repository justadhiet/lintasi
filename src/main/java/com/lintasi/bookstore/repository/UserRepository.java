package com.lintasi.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lintasi.bookstore.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {

}
