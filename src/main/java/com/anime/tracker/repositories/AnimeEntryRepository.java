package com.anime.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anime.tracker.entities.AnimeEntry;

public interface AnimeEntryRepository extends JpaRepository<AnimeEntry, Long> {

}