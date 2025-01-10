package com.anime.tracker.service;

import java.util.List;

import com.anime.tracker.entities.Anime;
import com.anime.tracker.repositories.AnimeRepository;

public class AnimeService
{
	private AnimeRepository animeRepository;

	public AnimeService(AnimeRepository animeRepository)
	{
		this.animeRepository = animeRepository;
	}

	public List<Anime> getAnime()
	{
		List<Anime> animes = this.animeRepository.findAll();
		return animes;
	}
}