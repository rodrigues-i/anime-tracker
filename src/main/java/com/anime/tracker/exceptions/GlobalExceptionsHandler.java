package com.anime.tracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler
{

	@ExceptionHandler(AnimeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String animeNotFoundException(AnimeNotFoundException ex)
	{
		return ex.getMessage();
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String userNotFoundException(UserNotFoundException ex)
	{
		return ex.getMessage();
	}

	@ExceptionHandler(AnimeEntryNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String animeEntryNotFoundException(AnimeEntryNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(EpisodeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String episodeNotFoundException(EpisodeNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(ReviewNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String reviewNotFoundException(ReviewNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(ReviewNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String seasonNotFoundException(SeasonNotFoundException ex) {
		return ex.getMessage();
	}
}