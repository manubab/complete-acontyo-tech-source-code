package com.product.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.product.entity.UserInfo;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
   public UserInfo findByUsername(String username);
}
