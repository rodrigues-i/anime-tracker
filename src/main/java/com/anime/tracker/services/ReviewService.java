package com.anime.tracker.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anime.tracker.entities.Review;
import com.anime.tracker.exceptions.ReviewNotFoundException;
import com.anime.tracker.repositories.ReviewRepository;

@Service
public class ReviewService {

	private ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	public List<Review> getReviews() {
		return reviewRepository.findAll();
	}

	public Review getReviewById(Long reviewId) {

		return reviewRepository.findById(reviewId)
				.orElseThrow(() -> new ReviewNotFoundException(reviewId));
	}

	public Review createReview(Review review) {
		return reviewRepository.save(review);
	}

    public Review updateReview(Review review, Long reviewId) {
		Review reviewToUpdate = reviewRepository.findById(reviewId)
									.orElseThrow(() -> new ReviewNotFoundException(reviewId));
		if(review.getRating() != 0)
			reviewToUpdate.setRating(review.getRating());
		if(review.getAnime() != null)
			reviewToUpdate.setAnime(review.getAnime());
		if(review.getContent() != null)
			reviewToUpdate.setContent(review.getContent());
		if(review.getUser() != null)
			reviewToUpdate.setUser(review.getUser());
		if(review.getReviewDate() != null)
			reviewToUpdate.setReviewDate(review.getReviewDate());
		return reviewRepository.save(reviewToUpdate);
    }

    public void deleteReview(Long reviewId) {
        Review reviewToDelete = reviewRepository.findById(reviewId)
									.orElseThrow(() -> new ReviewNotFoundException(reviewId));
		reviewRepository.delete(reviewToDelete);
    }
}