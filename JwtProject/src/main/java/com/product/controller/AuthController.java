package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.dio.AuthRequestDTO;
import com.product.dio.JwtResponseDTO;
import com.product.service.JwtService;

@RestController
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtService jwtService;
	
	@PostMapping("/api/v1/login")
	public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
	    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
	    if(authentication.isAuthenticated()){
	    String token=	jwtService.GenerateToken(authRequestDTO.getUsername());
	       return new JwtResponseDTO(token);
	    } else {
	        throw new UsernameNotFoundException("invalid user request..!!");
	    }
	}
}
