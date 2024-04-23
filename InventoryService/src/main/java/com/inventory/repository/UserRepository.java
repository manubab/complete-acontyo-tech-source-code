package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.entity.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
	public UserInfo findByUsername(String username);

}
