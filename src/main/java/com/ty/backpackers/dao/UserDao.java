package com.ty.backpackers.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.backpackers.dto.User;
import com.ty.backpackers.respository.UserRepository;

@Repository

public class UserDao {

	@Autowired
	private UserRepository repository;

	public User saveUser(User user) {

		return repository.save(user);
	}

	public User findUserById(int id) {

		Optional<User> optional = repository.findById(id);

		if (optional.isPresent()) {

			return optional.get();

		} else {
			return null;
		}

	}

	public User getUserByEmail(String email) {

		Optional<User> optional = repository.findByEmail(email);

		if (optional.isPresent()) {

			return optional.get();

		} else {
			return null;
		}
	}

	public User getUSerByPhone(long phone) {

		Optional<User> optional = repository.findByPhone(phone);

		if (optional.isPresent()) {

			return optional.get();

		} else {
			return null;
		}
	}

	public boolean deleteUserById(int id) {

		Optional<User> optional = repository.findById(id);

		if (optional.isPresent()) {

			repository.delete(optional.get());
			return true;

		} else {

			return false;
		}
	}

	public List<User> getAllUsers() {

		return repository.findAll();
	}

}
