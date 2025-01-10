package com.anime.tracker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anime.tracker.entities.Anime;
import com.anime.tracker.repositories.AnimeRepository;

@Service
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