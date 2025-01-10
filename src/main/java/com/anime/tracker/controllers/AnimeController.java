package com.anime.tracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anime.tracker.entities.Anime;
import com.anime.tracker.services.AnimeService;

@RestController
public class AnimeController {

	@Autowired
	private AnimeService animeService;

	@GetMapping("/animes")
	public List<Anime> getAnimes()
	{
		List<Anime> animes = this.animeService.getAnime();
		return animes;
	}
}
