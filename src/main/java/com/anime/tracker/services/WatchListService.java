package com.anime.tracker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anime.tracker.entities.WatchList;
import com.anime.tracker.exceptions.WatchListNotFoundException;
import com.anime.tracker.repositories.WatchListRepository;

@Service
public class WatchListService {

    private final WatchListRepository watchListRepository;

    public WatchListService(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    public List<WatchList> getAllWatchLists() {
        return watchListRepository.findAll();
    }

    public WatchList getWatchListById(Long watchListId) {
       return watchListRepository.findById(watchListId)
                            .orElseThrow(() -> new WatchListNotFoundException(watchListId));
    }

    public WatchList createWatchList(WatchList watchList) {
        return watchListRepository.save(watchList);
    }

    public WatchList updateWatchList(Long watchListId, WatchList watchList) {
        WatchList existingWatchList = watchListRepository.findById(watchListId)
                .orElseThrow(() -> new WatchListNotFoundException(watchListId));
        if(watchList.getAnimes() != null)
            existingWatchList.setAnimes(watchList.getAnimes());
        if(watchList.getName() != null)
            existingWatchList.setName(watchList.getName());
        if(watchList.getUser() != null)
            existingWatchList.setUser(watchList.getUser());
        return watchListRepository.save(existingWatchList);
    }

    public void deleteWatchListById(Long watchListId) {
        watchListRepository.findById(watchListId)
                .orElseThrow(() -> new WatchListNotFoundException(watchListId));
        watchListRepository.deleteById(watchListId);
    }
}
