package com.anime.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anime.tracker.entities.WatchList;

public interface WatchListRepository extends JpaRepository<WatchList, Long> {

}
