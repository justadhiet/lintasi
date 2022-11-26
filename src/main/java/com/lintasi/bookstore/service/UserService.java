package com.lintasi.bookstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintasi.bookstore.repository.UserRepository;
import com.lintasi.bookstore.model.UserModel;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<UserModel> listAllUser(){
		return userRepository.findAll();
	}
	
	public void saveUser(UserModel user) {
		userRepository.save(user);
	}
	
	public UserModel getUser(Integer id) {
		return userRepository.findById(id).get();
	}
	
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}
}

