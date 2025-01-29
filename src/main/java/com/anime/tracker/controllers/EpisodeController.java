package com.anime.tracker.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anime.tracker.entities.Episode;
import com.anime.tracker.services.EpisodeService;

@RestController
public class EpisodeController {

	private EpisodeService episodeService;

	public EpisodeController(EpisodeService episodeService) {
		this.episodeService = episodeService;
	}

	@GetMapping("/episodes")
	public CollectionModel<EntityModel<Episode>> getEpisodes() {
		List<EntityModel<Episode>> entityModel = episodeService.getEpisodes()
				.stream().map(episode -> EntityModel.of(episode,
						linkTo(methodOn(EpisodeController.class).getEpisodeById(episode.getId())).withSelfRel(),
						linkTo(methodOn(EpisodeController.class).getEpisodes()).withRel("episodes")))
						.collect(Collectors.toList());
		return CollectionModel.of(entityModel, linkTo(methodOn(EpisodeController.class).getEpisodes()).withSelfRel());	
	}

	@GetMapping("/episodes/{episodeId}")
	public ResponseEntity<EntityModel<Episode>> getEpisodeById(@PathVariable Long episodeId) {
		Episode episode = episodeService.getEpisodeById(episodeId);
		EntityModel<Episode> entityModel = EntityModel.of(episode,
				linkTo(methodOn(EpisodeController.class).getEpisodeById(episode.getId())).withSelfRel(),
				linkTo(methodOn(EpisodeController.class).getEpisodes()).withRel("episodes"));

		return ResponseEntity.ok(entityModel);
	}

	@PostMapping("/episodes")
	public ResponseEntity<EntityModel<Episode>> createEpisode(@RequestBody Episode episode) {
		Episode newEpisode = episodeService.createEpisode(episode);
		EntityModel<Episode> entityModel = EntityModel.of(episode,
				linkTo(methodOn(EpisodeController.class).getEpisodeById(episode.getId())).withSelfRel(),
				linkTo(methodOn(EpisodeController.class).getEpisodes()).withRel("episodes"));
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	@PutMapping("/episodes/{episodeId}")
	public ResponseEntity<EntityModel<Episode>> updateEpisode(@RequestBody Episode episode, @RequestParam Long episodeId) {
		Episode episodeUpdated = episodeService.updateEpisode(episode, episodeId);
		EntityModel<Episode> entityModel = EntityModel.of(episodeUpdated,
				linkTo(methodOn(EpisodeController.class).getEpisodeById(episodeUpdated.getId())).withSelfRel(),
				linkTo(methodOn(EpisodeController.class).getEpisodes()).withRel("episodes"));
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	@DeleteMapping("episodes/{episodeId}")
	public ResponseEntity<Void> deleteEpisode(@PathVariable Long episodeId) {
		episodeService.deleteEpisode(episodeId);

		return ResponseEntity.noContent().build();
	}
}