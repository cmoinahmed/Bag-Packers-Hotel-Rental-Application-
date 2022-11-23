package com.ty.backpackers.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.backpackers.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	Optional<User> findByPhone(long phone);

	
}
