package com.anime.tracker.exceptions;

public class AnimeNotFoundException extends RuntimeException
{
	public AnimeNotFoundException(Long id)
	{
		super("Could not find anime with id " + id);
	}
}