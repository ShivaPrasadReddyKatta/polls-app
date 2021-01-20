package com.shiva.polls.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shiva.polls.model.User;
import com.shiva.polls.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
										.orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail));
		
		return UserPrincipal.create(user);
	}
	
	public UserDetails loadUserById(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + userId));
		return UserPrincipal.create(user);
	}
	
	

}
