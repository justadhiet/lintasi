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

import com.lintasi.bookstore.model.Role;
import com.lintasi.bookstore.model.User;
import com.lintasi.bookstore.payload.request.UserRequest;
import com.lintasi.bookstore.payload.response.MessageResponse;
import com.lintasi.bookstore.service.RoleService;
import com.lintasi.bookstore.service.UserService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@GetMapping("")
	public List<User> list() {
		return userService.listAllUser();
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable Integer id) {
		try {
			User user = userService.getUser(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/")
	public ResponseEntity<?> add(@RequestBody UserRequest user) {
		try {
			userService.saveUser(getUserModelFromRequest(user));
			return ResponseEntity.ok(new MessageResponse("User insert successfully!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: User can't be insert! e:" + e.getMessage()));
		}
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody UserRequest user, @PathVariable Integer id){
		try {
			User existUser = userService.getUser(id);
			if(existUser!=null) {
				User update = getUserModelFromRequest(user);
				update.setJoinDate(existUser.getJoinDate());
				update.setIdUser(existUser.getIdUser());
				update.setPassword(existUser.getPassword());
				userService.saveUser(update);
				return ResponseEntity.ok(new MessageResponse("User update successfully!"));
			}
			return ResponseEntity.badRequest().body(new MessageResponse("Error: User doesn't exist!"));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new MessageResponse("Error: User can't be update! e:" + e.getMessage()));
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.ok(new MessageResponse("User delete successfully!"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: User can't be deleted! e:" + e.getMessage()));
		}
	}
	
	private User getUserModelFromRequest(UserRequest param) {
		User user = new User();
		user.setAddress(param.getAddress());
		user.setEmail(param.getEmail());
		user.setName(param.getName());
		user.setNoHp(param.getNoHp());
		user.setUsername(param.getUsername());
		user.setJoinDate(new Timestamp(new Date().getTime()));
		
		Role role = roleService.getRole(param.getRoleId());
		user.setRole(role);
		
		return user;
	}
}