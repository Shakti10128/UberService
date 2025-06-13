package org.shakti.uberreviewservice.Services.Impl;

import jakarta.transaction.Transactional;
import org.shakti.uberreviewservice.Exceptions.CustomError;
import org.shakti.uberreviewservice.Models.Review;
import org.shakti.uberreviewservice.Repositories.ReviewRepository;
import org.shakti.uberreviewservice.Services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void createReview(Review review) {
        try {
            reviewRepository.save(review);
        }
        catch (Exception e) {
            throw new CustomError("Error while the creating review", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return null;
    }

    @Override
    public List<Review> getAllReviews() {
        return List.of();
    }

    @Override
    public Review updateReview(Review review) {
        return null;
    }

    @Override
    public void deleteReviewById(Long reviewId) {

    }
}
