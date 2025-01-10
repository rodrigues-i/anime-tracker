package com.anime.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anime.tracker.entities.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

}