package com.acintyo.project.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.acintyo.project.entity.UserRegistrationForm;
import com.acintyo.project.repo.UserRegistrationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImp implements UserDetailsService {

	private final UserRegistrationRepository userRegistrationRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		return userRegistrationRepository.findByEmail(username) .orElseThrow(()-> new UsernameNotFoundException("user didn't have account please  register "));
	}

}
