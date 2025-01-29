package com.anime.tracker.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anime.tracker.entities.Season;
import com.anime.tracker.services.SeasonService;

@RestController
@RequestMapping("/seasons")
public class SeasonController {

    private SeasonService seasonService;

    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping
    public CollectionModel<EntityModel<Season>> getSeasons() {
        List<EntityModel<Season>> seasons = seasonService.getSeasons()
                .stream().map(season -> EntityModel.of(season,
                        linkTo(methodOn(SeasonController.class).getSeasonById(season.getId())).withSelfRel(),
                        linkTo(methodOn(SeasonController.class).getSeasons()).withRel("seasons")))
                .collect(Collectors.toList());

        return CollectionModel.of(seasons, linkTo(methodOn(SeasonController.class).getSeasons()).withSelfRel());
    }

    @GetMapping("/{seasonId}")
    public ResponseEntity<EntityModel<Season>> getSeasonById(@PathVariable Long seasonId) {
        Season season = seasonService.getSeasonById(seasonId);
        EntityModel<Season> entityModel = EntityModel.of(season,
                linkTo(methodOn(SeasonController.class).getSeasonById(season.getId())).withSelfRel(),
                linkTo(methodOn(SeasonController.class).getSeasons()).withRel("seasons"));

        return ResponseEntity.ok(entityModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Season>> createSeason(@RequestBody Season season) {
        Season seasonCreated = seasonService.createSeason(season);

        EntityModel<Season> entityModel = EntityModel.of(seasonCreated,
                linkTo(methodOn(SeasonController.class).getSeasonById(seasonCreated.getId())).withSelfRel(),
                linkTo(methodOn(SeasonController.class).getSeasons()).withRel("seasons"));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/{seasonId}")
    public ResponseEntity<EntityModel<Season>> updateSeason(@PathVariable Long seasonId, @RequestBody Season season) {
        Season seasonUpdated = seasonService.updateSeason(seasonId, season);

        EntityModel<Season> entityModel = EntityModel.of(seasonUpdated,
                linkTo(methodOn(SeasonController.class).getSeasonById(seasonUpdated.getId())).withSelfRel(),
                linkTo(methodOn(SeasonController.class).getSeasons()).withRel("seasons"));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/{seasonId}")
    public ResponseEntity<Void> deleteSeason(@PathVariable Long seasonId) {
        seasonService.deleteSeason(seasonId);

        return ResponseEntity.noContent().build();
    }
}