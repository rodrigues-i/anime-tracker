package com.anime.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anime.tracker.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}