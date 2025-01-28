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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anime.tracker.entities.Review;
import com.anime.tracker.services.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@GetMapping
	public CollectionModel<EntityModel<Review>> getReviews() {
		List<EntityModel<Review>> reviews = reviewService.getReviews()
				.stream().map(review -> EntityModel.of(review,
						linkTo(methodOn(ReviewController.class).getReviewById(review.getId())).withSelfRel(),
						linkTo(methodOn(ReviewController.class).getReviews()).withRel("reviews")))
				.collect(Collectors.toList());

		return CollectionModel.of(reviews, linkTo(methodOn(ReviewController.class).getReviews()).withSelfRel());
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<EntityModel<Review>> getReviewById(@PathVariable Long reviewId) {
		Review review = reviewService.getReviewById(reviewId);
		EntityModel<Review> entityModel = EntityModel.of(review,
				linkTo(methodOn(ReviewController.class).getReviewById(review.getId())).withSelfRel(),
				linkTo(methodOn(ReviewController.class).getReviews()).withRel("reviews"));

		return ResponseEntity.ok(entityModel);
	}

	@PostMapping
	public ResponseEntity<EntityModel<Review>> createREview(@RequestBody Review review) {
		Review reviewCreated = reviewService.createReview(review);

		EntityModel<Review> entityModel = EntityModel.of(reviewCreated,
				linkTo(methodOn(ReviewController.class).getReviewById(reviewCreated.getId())).withSelfRel(),
				linkTo(methodOn(ReviewController.class).getReviews()).withRel("reviews"));

		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<EntityModel<Review>> updateReview(@RequestBody Review review, @PathVariable Long reviewId) {
		Review reviewUpdated = reviewService.updateReview(review, reviewId);
		EntityModel<Review> entityModel = EntityModel.of(reviewUpdated,
				linkTo(methodOn(ReviewController.class).getReviewById(reviewUpdated.getId())).withSelfRel(),
				linkTo(methodOn(ReviewController.class).getReviews()).withRel("reviews"));
		return ResponseEntity
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
				.body(entityModel);
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
		reviewService.deleteReview(reviewId);
		return ResponseEntity.noContent().build();
	}
}