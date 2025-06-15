package org.shakti.uberreviewservice.Controllers;

import org.shakti.uberreviewservice.Dtos.ReviewDTO;
import org.shakti.uberreviewservice.Models.Review;
import org.shakti.uberreviewservice.Services.ReviewService;
import org.shakti.uberreviewservice.Utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("reviews")
    public ResponseEntity<ApiResponse<Void>> createReviewHandler(@RequestBody ReviewDTO reviewDTO) {
        reviewService.createReview(reviewDTO);
        ApiResponse response = new ApiResponse<>(true,"Review created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/reviews/{id}")
    public ResponseEntity<ApiResponse> getReviewByIdHandler(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        ApiResponse response = new ApiResponse<>(true,"Review retrieved successfully", review);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/reviews")
    public ResponseEntity<ApiResponse> getAllReviewHandler() {
        List<Review> reviews = reviewService.getAllReviews();
        ApiResponse response = new ApiResponse<>(true,"All Reviews retrieved successfully", reviews);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/reviews")
    public ResponseEntity<ApiResponse> updateReviewHandler(@RequestBody Review review) {
        reviewService.updateReview(review);
        ApiResponse response = new ApiResponse<>(true,"Review updated successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<ApiResponse> deleteReviewHandler(@PathVariable Long id) {
        reviewService.deleteReviewById(id);
        ApiResponse response = new ApiResponse<>(true,"Review deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
