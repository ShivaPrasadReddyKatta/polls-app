package com.shiva.polls.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.polls.exception.ResourceNotFoundException;
import com.shiva.polls.model.User;
import com.shiva.polls.payload.PagedResponse;
import com.shiva.polls.payload.PollResponse;
import com.shiva.polls.payload.UserIdentityAvailability;
import com.shiva.polls.payload.UserProfile;
import com.shiva.polls.payload.UserSummary;
import com.shiva.polls.repository.PollRepository;
import com.shiva.polls.repository.UserRepository;
import com.shiva.polls.repository.VoteRepository;
import com.shiva.polls.security.CurrentUser;
import com.shiva.polls.security.UserPrincipal;
import com.shiva.polls.service.PollService;
import com.shiva.polls.util.AppConstants;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PollRepository pollRepository;
	
	@Autowired
	VoteRepository voteRepository;
	
	@Autowired
	PollService pollService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
		return userSummary;
	}
	
	@GetMapping("/user/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}
	
	@GetMapping("/user/checkEmailAvailability")
	public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
		Boolean isAvailable = !userRepository.existsByUsername(email);
		return new UserIdentityAvailability(isAvailable);
	}
	
	@GetMapping("/users/{username}")
	public UserProfile getUserProfile(@RequestParam(value = "username") String username) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
		long pollCount = pollRepository.countByCreatedBy(user.getId());
		
		long voteCount = voteRepository.countByUserId(user.getId());
		
		UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), pollCount, voteCount);
		return userProfile;
	}
	
	@GetMapping("/users/{username}/polls")
	public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
														 @CurrentUser UserPrincipal currentUser,
														 @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
														 @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
		return pollService.getPollsCreatedBy(username, currentUser, page, size);
	}
	
	@GetMapping("/users/{username}/votes")
	public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable(value = "username") String username,
														 @CurrentUser UserPrincipal currentUser,
														 @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
														 @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size){
		return pollService.getPollsVotedBy(username, currentUser, page, size);
	}
}
