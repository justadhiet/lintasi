package com.lintasi.bookstore.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lintasi.bookstore.model.RoleModel;
import com.lintasi.bookstore.service.RoleService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@GetMapping("")
	public List<RoleModel> list(){
		return roleService.listAllRole();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RoleModel> get(@PathVariable Integer id){
		try {
			RoleModel role = roleService.getRole(id);
			return new ResponseEntity<RoleModel>(role, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RoleModel>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("")
	public void add(@RequestBody RoleModel role) {
		roleService.saveRole(role);
	}

}
