package com.anime.tracker.controllers;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anime.tracker.entities.WatchList;
import com.anime.tracker.services.WatchListService;

@RestController
@RequestMapping("/watchlists")
public class WatachListController {

    private final WatchListService watchListService;

    public WatachListController(WatchListService watchListService) {
        this.watchListService = watchListService;
    }

    @GetMapping
    public CollectionModel<EntityModel<WatchList>> getAllWatchLists() {
        List<WatchList> watchLists = watchListService.getAllWatchLists();
        List<EntityModel<WatchList>> watchListModels = watchLists.stream()
                .map(watchList -> EntityModel.of(watchList,
                        linkTo(methodOn(WatachListController.class).getWatchListById(watchList.getId())).withSelfRel(),
                        linkTo(methodOn(WatachListController.class).getAllWatchLists()).withRel("watchlists")))
                .collect(Collectors.toList());
        return CollectionModel.of(watchListModels, linkTo(methodOn(WatachListController.class).getAllWatchLists()).withSelfRel());
    }

    @GetMapping("/{watchListId}")
    public ResponseEntity<EntityModel<WatchList>> getWatchListById(@PathVariable Long watchListId) {
        WatchList watchList = watchListService.getWatchListById(watchListId);
        EntityModel<WatchList> watchListModel = EntityModel.of(watchList,
                linkTo(methodOn(WatachListController.class).getWatchListById(watchListId)).withSelfRel(),
                linkTo(methodOn(WatachListController.class).getAllWatchLists()).withRel("watchlists"));
        return ResponseEntity.ok(watchListModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<WatchList>> createWatchList(WatchList watchList) {
        WatchList createdWatchList = watchListService.createWatchList(watchList);

        EntityModel<WatchList> watchListModel = EntityModel.of(createdWatchList,
                linkTo(methodOn(WatachListController.class).getWatchListById(createdWatchList.getId())).withSelfRel(),
                linkTo(methodOn(WatachListController.class).getAllWatchLists()).withRel("watchlists"));
        return ResponseEntity.created(watchListModel.getRequiredLink("self").toUri()).body(watchListModel);
    }

    @PutMapping("/{watchListId}")
    public ResponseEntity<EntityModel<WatchList>> updateWatchList(@PathVariable Long watchListId, WatchList watchList) {
        WatchList updatedWatchList = watchListService.updateWatchList(watchListId, watchList);
        EntityModel<WatchList> watchListModel = EntityModel.of(updatedWatchList,
                linkTo(methodOn(WatachListController.class).getWatchListById(watchListId)).withSelfRel(),
                linkTo(methodOn(WatachListController.class).getAllWatchLists()).withRel("watchlists"));
        return ResponseEntity
            .created(EntityModel.of(updatedWatchList).getRequiredLink("self").toUri())
            .body(watchListModel);
    }

    @DeleteMapping("/{watchListId}")
    public ResponseEntity<Void> deleteWatchList(@PathVariable Long watchListId) {
        watchListService.deleteWatchListById(watchListId);
        return ResponseEntity.noContent().build();
    }
}
