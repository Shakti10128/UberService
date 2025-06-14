package org.shakti.uberreviewservice.Adaptors;

import org.shakti.uberreviewservice.Dtos.ReviewDTO;
import org.shakti.uberreviewservice.Models.Booking;
import org.shakti.uberreviewservice.Models.Review;

public class ReviewMapper {
    private ReviewMapper() {}
    public static Review toEntity(ReviewDTO reviewDTO) {
        return Review.builder()
                .content(reviewDTO.getContent())
                .rating(reviewDTO.getRating())
                .build();
    }
    public static ReviewDTO toDto(Review review,Long bookingId) {
        return ReviewDTO.builder()
                .content(review.getContent())
                .rating(review.getRating())
                .bookingId(bookingId)
                .build();
    }
}
