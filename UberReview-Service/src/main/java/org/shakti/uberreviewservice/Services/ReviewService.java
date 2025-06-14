package org.shakti.uberreviewservice.Services;

import org.shakti.uberreviewservice.Dtos.ReviewDTO;
import org.shakti.uberreviewservice.Models.Review;

import java.util.List;

public interface ReviewService {
    /*
    * Params: Review Object that need to be save or create
    * The method create a new review
    * Return void
    * */
    void createReview(ReviewDTO reviewDTO);

    Review getReviewById(Long reviewId);

    List<Review> getAllReviews();

    Review updateReview(Review review);

    void deleteReviewById(Long reviewId);
}
