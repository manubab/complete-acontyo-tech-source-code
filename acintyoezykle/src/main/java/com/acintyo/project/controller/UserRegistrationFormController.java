package com.acintyo.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acintyo.project.entity.UserRegistrationForm;
import com.acintyo.project.model.AuthenticationResponse;
import com.acintyo.project.service.UserRegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserRegistrationFormController {

	private final UserRegistrationService userRegistrationService;

	@PostMapping("/register")
	public AuthenticationResponse registerUser(@RequestBody UserRegistrationForm userRegistrationForm) {

		return userRegistrationService.register(userRegistrationForm);
	}

	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody UserRegistrationForm request) {
		return userRegistrationService.authenticate(request);
	}
}
