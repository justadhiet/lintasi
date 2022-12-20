package com.lintasi.bookstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lintasi.bookstore.repository.UserRepository;
import com.lintasi.bookstore.model.User;

@Service
@Transactional
public class UserService{

	@Autowired
	private UserRepository userRepository;
	
	public List<User> listAllUser(){
		return userRepository.findAll();
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	public User getUser(Integer id) {
		return userRepository.findById(id).get();
	}
	
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

}

 