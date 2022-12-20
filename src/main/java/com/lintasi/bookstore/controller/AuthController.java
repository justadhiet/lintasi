package com.lintasi.bookstore.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lintasi.bookstore.model.ERole;
import com.lintasi.bookstore.model.Role;
import com.lintasi.bookstore.model.User;
import com.lintasi.bookstore.payload.request.LoginRequest;
import com.lintasi.bookstore.payload.request.SignupRequest;
import com.lintasi.bookstore.payload.response.JwtResponse;
import com.lintasi.bookstore.payload.response.MessageResponse;
import com.lintasi.bookstore.repository.RoleRepository;
import com.lintasi.bookstore.repository.UserRepository;
import com.lintasi.bookstore.security.config.JwtUtils;
import com.lintasi.bookstore.security.service.UserDetailsImpl;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		String strRoles = signUpRequest.getRole();
		Role roles = new Role();

		if (strRoles == null) {
			roles = roleRepository.findByNama(ERole.ROLE_USER).get();
		} else {
			switch (strRoles) {
			case "admin":
				Role adminRole = roleRepository.findByNama(ERole.ROLE_ADMIN).get();
				roles = adminRole;
				break;
			case "manager":
				Role modRole = roleRepository.findByNama(ERole.ROLE_MANAGER).get();
				roles = modRole;
				break;
			case "editor":
				Role editRole = roleRepository.findByNama(ERole.ROLE_EDITOR).get();
				roles = editRole;
				break;
			default:
				Role userRole = roleRepository.findByNama(ERole.ROLE_USER).get();
				roles = userRole;
			}
		}

		user.setRole(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
