package com.acintyo.project.service;

import com.acintyo.project.entity.UserRegistrationForm;
import com.acintyo.project.model.AuthenticationResponse;

public interface UserRegistrationService
{
	public AuthenticationResponse register(UserRegistrationForm userRegistrationForm);

	public AuthenticationResponse authenticate(UserRegistrationForm request);

}
