package com.lintasi.bookstore.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lintasi.bookstore.model.RoleModel;
import com.lintasi.bookstore.model.UserModel;
import com.lintasi.bookstore.request.UserRequest;
import com.lintasi.bookstore.service.RoleService;
import com.lintasi.bookstore.service.UserService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@GetMapping("")
	public List<UserModel> list() {
		return userService.listAllUser();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserModel> get(@PathVariable Integer id) {
		try {
			UserModel user = userService.getUser(id);
			return new ResponseEntity<UserModel>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<UserModel>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public void add(@RequestBody UserRequest user) {
		userService.saveUser(getUserModelFromRequest(user));
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody UserRequest user, @PathVariable Integer id){
		try {
			UserModel existUser = userService.getUser(id);
			if(existUser!=null) {
				UserModel update = getUserModelFromRequest(user);
				update.setJoinDate(existUser.getJoinDate());
				update.setIdUser(existUser.getIdUser());
				userService.saveUser(update);
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		userService.deleteUser(id);
	}
	
	private UserModel getUserModelFromRequest(UserRequest param) {
		UserModel userModel = new UserModel();
		userModel.setAddress(param.getAddress());
		userModel.setEmail(param.getEmail());
		userModel.setName(param.getName());
		userModel.setNoHp(param.getNoHp());
		userModel.setUsername(param.getUsername());
		userModel.setJoinDate(new Timestamp(new Date().getTime()));
		
		RoleModel roleModel = roleService.getRole(param.getRoleId());
		userModel.setIdRole(roleModel);
		
		return userModel;
	}
}