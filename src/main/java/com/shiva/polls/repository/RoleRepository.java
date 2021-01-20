package com.shiva.polls.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shiva.polls.model.Role;
import com.shiva.polls.model.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(RoleName roleUser);

}
