package com.lintasi.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lintasi.bookstore.model.ERole;
import com.lintasi.bookstore.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByNama(ERole name);
}
