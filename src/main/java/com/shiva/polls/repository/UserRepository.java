package com.shiva.polls.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiva.polls.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsernameOrEmail(String username, String email);
	
	Optional<User> findByUsername(String username);
	
	List<User> findByIdIn(List<Long> userIds);
	
	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}
