package com.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.dto.AuthRequestDTO;
import com.inventory.dto.JwtResponseDTO;
import com.inventory.entity.UserInfo;
import com.inventory.repository.UserRepository;
import com.inventory.service.JwtService;

@RestController
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	JwtService jwtService;

	@PostMapping("/api/v1/login")
	public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
		if (authentication.isAuthenticated()) {
			String token = jwtService.GenerateToken(authRequestDTO.getUsername());

			return new JwtResponseDTO(token);
		} else {
			throw new UsernameNotFoundException("invalid user request..!!");
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/ping")
	public String test() {
		try {
			return "Welcome";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Autowired
	UserRepository repo;

	@PostMapping("/save")
	public String saveUser(@RequestBody UserInfo info) {

		repo.save(info);
		return "saved currectly";
	}

}
