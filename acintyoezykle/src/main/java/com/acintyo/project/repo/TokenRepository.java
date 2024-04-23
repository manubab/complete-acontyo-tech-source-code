package com.acintyo.project.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acintyo.project.entity.Token;
@Repository
public interface TokenRepository extends JpaRepository<Token,Integer > {

	
	Optional<Token> findByToken(String token);
	
	
	
	@Query("""
			select t from Token t inner join UserRegistrationForm u on t.user.id = u.id
			where t.user.id = :userId and t.loggedOut = false
			""")
	List<Token> findAllTokensByUser(Integer userId);

}
