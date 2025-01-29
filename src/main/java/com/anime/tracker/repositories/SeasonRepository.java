package com.anime.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anime.tracker.entities.Season;

public interface SeasonRepository extends JpaRepository<Season, Long> {

}
