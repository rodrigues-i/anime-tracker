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
}