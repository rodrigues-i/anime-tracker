package com.anime.tracker.exceptions;

public class WatchListNotFoundException extends RuntimeException {

    public WatchListNotFoundException(Long watchListId) {
        super("Could not find watchlist for id " + watchListId);
    }

}
