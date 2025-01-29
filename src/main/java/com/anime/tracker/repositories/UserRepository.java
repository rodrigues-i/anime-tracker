package com.anime.tracker.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anime.tracker.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

}