package org.shakti.uberreviewservice.Controllers;

import org.shakti.uberreviewservice.Dtos.ReviewDTO;
import org.shakti.uberreviewservice.Models.Review;
import org.shakti.uberreviewservice.Services.ReviewService;
import org.shakti.uberreviewservice.Utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create-review")
    public ResponseEntity<ApiResponse<Void>> createReviewHandler(@RequestBody ReviewDTO reviewDTO) {
        reviewService.createReview(reviewDTO);
        ApiResponse response = new ApiResponse<>(true,"Review created successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
