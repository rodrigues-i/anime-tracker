package com.anime.tracker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anime.tracker.entities.User;
import com.anime.tracker.exceptions.UserNotFoundException;
import com.anime.tracker.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	public List<User> getUsers() {
		return this.userRepository.findAll();
	}

	public User getUserById(Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		return user;
	}
}