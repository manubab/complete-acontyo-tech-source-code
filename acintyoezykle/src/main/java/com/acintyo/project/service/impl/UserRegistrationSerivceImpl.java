package com.acintyo.project.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.acintyo.project.entity.Token;
import com.acintyo.project.entity.UserAccount;
import com.acintyo.project.entity.UserRegistrationForm;
import com.acintyo.project.jwtservice.JWTService;
import com.acintyo.project.model.AuthenticationResponse;
import com.acintyo.project.repo.TokenRepository;
import com.acintyo.project.repo.UserRegistrationRepository;
import com.acintyo.project.service.UserRegistrationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRegistrationSerivceImpl implements UserRegistrationService {

	private final UserRegistrationRepository registrationRepository;

	private final AuthenticationManager authenticationManager;

	private final JWTService jwtService;

	private final TokenRepository tokenRepository;

	private final PasswordEncoder passwordEncoder;

	@Override
	public AuthenticationResponse register(UserRegistrationForm userRegistrationForm) {

		if (registrationRepository.findByEmail(userRegistrationForm.getEmail()).isPresent()) {

			return new AuthenticationResponse(null, "user already register to that mail");
		}


		UserAccount account = new UserAccount();

		userRegistrationForm.setPassword(passwordEncoder.encode(userRegistrationForm.getPassword()));
		userRegistrationForm.setConfirmPassword(passwordEncoder.encode(userRegistrationForm.getConfirmPassword()));
		userRegistrationForm.setRegistrationDate(LocalDate.now());

//		account.setAccountNumber(passwordEncoder.encode(ac.getAccountNumber()));
//		account.setIfscCode(passwordEncoder.encode(ac.getIfscCode()));
		account.setRegistrationDate(LocalDate.now());

		userRegistrationForm.setUserAccount(account);

		userRegistrationForm = registrationRepository.save(userRegistrationForm);
		String token = jwtService.generateJwtToken(userRegistrationForm);
		saveUserToken(token, userRegistrationForm);
		return new AuthenticationResponse(token, "User registration was successful");
	}

	private void saveUserToken(String token, UserRegistrationForm userRegistrationForm) {

		Token token1 = new Token();
		token1.setToken(token);
		token1.setLoggedOut(false);
		token1.setUser(userRegistrationForm);
		tokenRepository.save(token1);
	}

	@Override
	public AuthenticationResponse authenticate(UserRegistrationForm request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		UserRegistrationForm user = registrationRepository.findByEmail(request.getEmail()).orElseThrow();

		String jwt = jwtService.generateJwtToken(user);

		revokeAllTokenByUser(user);

		saveUserToken(jwt, user);

		return new AuthenticationResponse(jwt, "User login was successful");
		
	}

	private void revokeAllTokenByUser(UserRegistrationForm user) {
		List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
		if (validTokens.isEmpty()) {
			return;
		}

		validTokens.forEach(t -> {
			t.setLoggedOut(true);
		});

		tokenRepository.saveAll(validTokens);
	}
}

