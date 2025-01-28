package com.anime.tracker.exceptions;

public class EpisodeNotFoundException extends RuntimeException {

	public EpisodeNotFoundException(Long episodeId) {
		super("Could not find episode for id " + episodeId);
	}
}
