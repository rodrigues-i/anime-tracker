package com.anime.tracker.exceptions;

public class ReviewNotFoundException extends RuntimeException {

	public ReviewNotFoundException(Long reviewId) {
		super("Could not find review for id " + reviewId);
	}
}