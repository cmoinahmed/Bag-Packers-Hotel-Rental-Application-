package com.ty.backpackers.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.backpackers.dto.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Optional<Admin> findByEmail(String email);

	Optional<Admin> findByPhone(long phone);
}
