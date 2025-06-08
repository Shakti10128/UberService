package org.shakti.uberreviewservice.Services;

import lombok.AllArgsConstructor;
import org.shakti.uberreviewservice.Models.Review;
import org.shakti.uberreviewservice.Repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReviewService implements CommandLineRunner {

    private ReviewRepository reviewRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("************************");
        Review review = Review.builder().build();
        review.setRating(4.5);
        review.setContent("new review");
        reviewRepository.save(review);
    }
}
