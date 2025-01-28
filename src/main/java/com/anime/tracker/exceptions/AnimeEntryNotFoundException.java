package com.anime.tracker.exceptions;

public class AnimeEntryNotFoundException extends RuntimeException {

	public AnimeEntryNotFoundException(Long id) {
		super("Could not find AnimeEntry with id " + id);
	}
}