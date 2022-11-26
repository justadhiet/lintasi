package com.lintasi.bookstore.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintasi.bookstore.model.RoleModel;
import com.lintasi.bookstore.repository.RoleRepository;

@Service
@Transactional
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<RoleModel> listAllRole(){
		return roleRepository.findAll();
	}
	
	public void saveRole(RoleModel role) {
		roleRepository.save(role);
	}
	
	public RoleModel getRole(Integer id) {
		return roleRepository.findById(id).get();
	}
	
	public void deleteRole(Integer id) {
		roleRepository.deleteById(id);
	}

}
