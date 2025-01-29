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
import org.springframework.web.bind.annotation.RestController;

import com.anime.tracker.entities.AnimeEntry;
import com.anime.tracker.exceptions.AnimeEntryNotFoundException;
import com.anime.tracker.services.AnimeEntryService;

@RestController
public class AnimeEntryController {

	private AnimeEntryService animeEntryService;

	public AnimeEntryController(AnimeEntryService animeEntryService) {
		this.animeEntryService = animeEntryService;
	}

	@GetMapping("/animeEntry")
	public CollectionModel<EntityModel<AnimeEntry>> getAnimeEntries() {
		List<EntityModel<AnimeEntry>> animeEntries = animeEntryService.getAnimeEntries()
				.stream().map(animeEntry -> EntityModel.of(animeEntry,
						linkTo(methodOn(AnimeEntryController.class).getAnimeEntryById(animeEntry.getId())).withSelfRel(),
						linkTo(methodOn(AnimeEntryController.class).getAnimeEntries()).withRel("animeEntries")))
				.collect(Collectors.toUnmodifiableList());

		return CollectionModel.of(animeEntries, linkTo(methodOn(AnimeEntryController.class).getAnimeEntries()).withSelfRel());
	}

	@GetMapping("/animeEntry/{animeEntryId}")
	public EntityModel<AnimeEntry> getAnimeEntryById(@PathVariable Long animeEntryId) {
		AnimeEntry animeEntry = animeEntryService.getAnimeEntryById(animeEntryId)
				.orElseThrow(() -> new AnimeEntryNotFoundException(animeEntryId));

		return EntityModel.of(animeEntry,
				linkTo(methodOn(AnimeEntryController.class).getAnimeEntryById(animeEntry.getId())).withSelfRel(),
				linkTo(methodOn(AnimeEntryController.class).getAnimeEntries()).withRel("animeEntries"));
	}

	@PostMapping("/animeEntry")
	public ResponseEntity<?> createAnimeEntry(@RequestBody AnimeEntry animeEntry) {
		AnimeEntry newAnimeEntry = animeEntryService.createAnimeEntry(animeEntry);
		EntityModel<AnimeEntry> entityModel = EntityModel.of(newAnimeEntry,
				linkTo(methodOn(AnimeEntryController.class).getAnimeEntryById(newAnimeEntry.getId())).withSelfRel(),
				linkTo(methodOn(AnimeEntryController.class).getAnimeEntries()).withRel("animeEntries"));
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	@PutMapping("/animeEntry/{animeEntryId}")
	public ResponseEntity<EntityModel<AnimeEntry>> updateAnimeEntry(@RequestBody AnimeEntry animeEntry, @PathVariable Long animeEntryId) {
		AnimeEntry updatedAnimeEntry = animeEntryService.updateAnimeEntry(animeEntryId, animeEntry);

		EntityModel<AnimeEntry> entityModel = EntityModel.of(updatedAnimeEntry,
				linkTo(methodOn(AnimeEntryController.class).getAnimeEntryById(updatedAnimeEntry.getId())).withSelfRel(),
				linkTo(methodOn(AnimeEntryController.class).getAnimeEntries()).withRel("animeEntries"));

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	@DeleteMapping("animeEntry/{animeEntryId}")
	public ResponseEntity<Void> deleteAnimeEntry(@PathVariable Long animeEntryId) {
		animeEntryService.deleteAnimeEntry(animeEntryId);
		return ResponseEntity.noContent().build();
	}
}