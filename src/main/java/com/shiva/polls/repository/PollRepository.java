package com.shiva.polls.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shiva.polls.model.Poll;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long>{
	
	Optional<Poll> findById(Long pollId);
	
	List<Poll> findByIdIn(List<Long> pollIds);
	
	List<Poll> findByIdIn(List<Long> pollIds, Sort sort);
	
	long countByCreatedBy(Long userId);
	
	Page<Poll> findByCreatedBy(Long userId, Pageable pageable);

}
