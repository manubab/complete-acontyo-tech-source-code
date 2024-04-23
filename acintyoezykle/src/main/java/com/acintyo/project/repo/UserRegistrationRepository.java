package com.acintyo.project.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acintyo.project.entity.UserRegistrationForm;

public interface UserRegistrationRepository extends JpaRepository<UserRegistrationForm, Integer>
{
	Optional<UserRegistrationForm> findByEmail(String email);

}
