package com.anime.tracker.exceptions;

public class SeasonNotFoundException extends RuntimeException {


    public SeasonNotFoundException(Long seasonId) {
        super("Could not find season for id " + seasonId);
    }

}
