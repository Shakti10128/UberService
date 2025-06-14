package org.shakti.uberreviewservice.Services.Impl;

import jakarta.transaction.Transactional;
import org.shakti.uberreviewservice.Adaptors.ReviewMapper;
import org.shakti.uberreviewservice.Dtos.ReviewDTO;
import org.shakti.uberreviewservice.Exceptions.CustomError;
import org.shakti.uberreviewservice.Models.Booking;
import org.shakti.uberreviewservice.Models.Review;
import org.shakti.uberreviewservice.Repositories.BookingRepository;
import org.shakti.uberreviewservice.Repositories.ReviewRepository;
import org.shakti.uberreviewservice.Services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, BookingRepository bookingRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void createReview(ReviewDTO reviewDTO) {
        try {
            // first check bookingId is correct or not
            Booking booking = bookingRepository.findById(reviewDTO.getBookingId())
                    .orElseThrow(() -> new CustomError("Booking not found with bookingId: " + reviewDTO.getBookingId(), HttpStatus.NOT_FOUND));

            System.out.println("converting dto to entity");
            Review review = ReviewMapper.toEntity(reviewDTO);
            // before saving id is null
            System.out.println("saving review");
            reviewRepository.save(review);
            // after saving id will be auto-generated via JPA, coz before save the entity to DB, JPA will create and id
            // and coz of that database review object will be in sync with DB

            // save the review id into booking table corresponding to the booking
            booking.setReview(review);
            bookingRepository.save(booking);
            System.out.println(review.getId());
        }
        catch (CustomError e) {
            throw e; // Let specific errors pass through as they are
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
