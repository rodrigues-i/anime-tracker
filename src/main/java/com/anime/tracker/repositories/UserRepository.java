package com.anime.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anime.tracker.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}