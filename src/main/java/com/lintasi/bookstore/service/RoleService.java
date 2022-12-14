package com.lintasi.bookstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintasi.bookstore.model.Role;
import com.lintasi.bookstore.repository.RoleRepository;

@Service
@Transactional
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> listAllRole(){
		return roleRepository.findAll();
	}
	
	public void saveRole(Role role) {
		roleRepository.save(role);
	}
	
	public Role getRole(Integer id) {
		return roleRepository.findById(id).get();
	}
	
	public void deleteRole(Integer id) {
		roleRepository.deleteById(id);
	}

}
