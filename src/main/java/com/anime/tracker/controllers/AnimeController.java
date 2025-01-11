package com.anime.tracker.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.anime.tracker.entities.Anime;
import com.anime.tracker.exceptions.AnimeNotFoundException;
import com.anime.tracker.services.AnimeService;

@RestController
public class AnimeController {

	@Autowired
	private AnimeService animeService;

	@GetMapping("/animes")
	public CollectionModel<EntityModel<Anime>> getAnimes()
	{
		List<EntityModel<Anime>> animes = this.animeService.getAnime()
				.stream().map(anime -> EntityModel.of(anime,
						linkTo(methodOn(AnimeController.class).getAnimeById(anime.getId())).withSelfRel(),
						linkTo(methodOn(AnimeController.class).getAnimes()).withRel("animes")))
						.collect(Collectors.toList());
		return CollectionModel.of(animes, linkTo(methodOn(AnimeController.class).getAnimes()).withSelfRel());
	}

	@GetMapping("/animes/{id}")
	public EntityModel<Anime> getAnimeById(@PathVariable Long id) {

		Anime anime = this.animeService.getAnimeById(id)
				.orElseThrow(() -> new AnimeNotFoundException(id));
		return EntityModel.of(anime,
				linkTo(methodOn(AnimeController.class).getAnimeById(anime.getId())).withSelfRel(),
				linkTo(methodOn(AnimeController.class).getAnimes()).withRel("animes"));
	}
}
