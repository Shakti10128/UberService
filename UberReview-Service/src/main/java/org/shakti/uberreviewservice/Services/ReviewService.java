package org.shakti.uberreviewservice.Services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.shakti.uberreviewservice.Models.Booking;
import org.shakti.uberreviewservice.Models.Review;
import org.shakti.uberreviewservice.Repositories.BookingRepository;
import org.shakti.uberreviewservice.Repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService implements CommandLineRunner {

    private final BookingRepository bookingRepository;
    private ReviewRepository reviewRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("************************");
        Review review = Review.builder()
                .content("Amazing ride quality")
                .rating(4.5)
                .build();

        Booking booking = Booking.builder()
                .review(review)
                .startTime(new Date())
                .endTime(new Date())
                .build();

        reviewRepository.save(review);
        bookingRepository.save(booking);

        List<Review> reviews = reviewRepository.findAll();
        for (Review r : reviews) {
            System.out.println(r.getContent());
        }
    }
}
