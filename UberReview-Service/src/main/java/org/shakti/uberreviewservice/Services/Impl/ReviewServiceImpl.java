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

            Review review = ReviewMapper.toEntity(reviewDTO);
            // before saving id is null
            reviewRepository.save(review);
            // after saving id will be auto-generated via JPA, coz before save the entity to DB, JPA will create and id
            // and coz of that database review object will be in sync with DB

            // save the review id into booking table corresponding to the booking
            booking.setReview(review);
            bookingRepository.save(booking);
        }
        catch (Exception e) {
            if(e instanceof CustomError) throw e;
            throw new CustomError("Error while creating review", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Review getReviewById(Long reviewId) {
        try{
            return reviewRepository.findById(reviewId).orElseThrow(
                    ()-> new CustomError("Review not found with reviewId: " + reviewId, HttpStatus.NOT_FOUND));
        }
        catch (Exception e) {
            if(e instanceof CustomError) throw e;
            throw new CustomError("Error while getting review by id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<Review> getAllReviews() {
        try{
            return reviewRepository.findAll();
        }
        catch (Exception e) {
            throw new CustomError("Error while getting all reviews", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateReview(Review review) {
        try {
            Review existingReview = reviewRepository.findById(review.getId())
                    .orElseThrow(() -> new CustomError("Review not found with reviewId: " + review.getId(), HttpStatus.NOT_FOUND));

            // Update the fields you want from 'review' to 'existingReview'
            existingReview.setRating(review.getRating());
            existingReview.setContent(review.getContent());

            reviewRepository.save(existingReview);
        }
        catch (Exception e) {
            if(e instanceof CustomError) throw e;
            throw new CustomError("Error while updating review", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteReviewById(Long reviewId) {
        try{
            Review existingReview = reviewRepository.findById(reviewId)
                    .orElseThrow(() -> new CustomError("Review not found with reviewId: " + reviewId, HttpStatus.NOT_FOUND));

            // finding the booking corresponding to the review
            Booking booking = bookingRepository.findBookingByReviewId(reviewId).orElseThrow(()-> new CustomError("There is no booking for the reviewId: " + reviewId, HttpStatus.NOT_FOUND));
            // make null the review in booking, coz we are going to delete the review corresponding to the booking
            booking.setReview(null);
            bookingRepository.save(booking);

            reviewRepository.delete(existingReview);
        }
        catch (Exception e) {
            if(e instanceof CustomError) throw e;
            throw new CustomError("Error while deleting review by id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
