package com.anime.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anime.tracker.entities.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

}