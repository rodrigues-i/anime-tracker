package com.anime.tracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.anime.tracker.entities.Review;
import com.anime.tracker.exceptions.ReviewNotFoundException;
import com.anime.tracker.repositories.ReviewRepository;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        // No need to initialize mocks manually, MockitoExtension does it automatically

    }

    @Test
    public void testGetReviews() {
        Review review1 = new Review();
        Review review2 = new Review();

        when(reviewRepository.findAll()).thenReturn(Arrays.asList(review1, review2));

        List<Review> result = reviewService.getReviews();

        assertEquals(2, result.size());
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    public void testGetReviewById() {
        Review review = new Review();
        review.setId(1L);

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        Review result = reviewService.getReviewById(1L);

        assertNotNull(result);
        assertEquals(review, result);
        verify(reviewRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateReview() {
        Review review = new Review();

        when(reviewRepository.save(review)).thenReturn(review);

        Review result = reviewService.createReview(review);

        assertNotNull(result);
        assertEquals(review, result);
        verify(reviewRepository, times(1)).save(review);
    }

    @Test
    public void testUpdateReview() {
        Review existingReview = new Review();
        existingReview.setId(1L);
        existingReview.setContent("Old Content");
        existingReview.setRating(3);

        Review updageReview = new Review();
        updageReview.setId(1L);
        updageReview.setContent("New Content");
        updageReview.setRating(5);

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(existingReview));
        when(reviewRepository.save(existingReview)).thenReturn(existingReview);

        Review result = reviewService.updateReview(updageReview, 1L);

        assertEquals("New Content", result.getContent());
        assertEquals(5, result.getRating());
        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).save(existingReview);
    }

    @Test
    public void testUpdateReviewNotFound() {
        Review review = new Review();
        review.setId(1L);
        review.setContent("New Content");
        review.setRating(5);

        when(reviewRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ReviewNotFoundException.class, () -> {
            reviewService.updateReview(review, 1L);
        });
    }

    @Test
    public void testDeleteReview() {
        Review review = new Review();
        review.setId(1L);

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        reviewService.deleteReview(1L);

        verify(reviewRepository, times(1)).findById(1L);
        verify(reviewRepository, times(1)).delete(review);
    }
}
