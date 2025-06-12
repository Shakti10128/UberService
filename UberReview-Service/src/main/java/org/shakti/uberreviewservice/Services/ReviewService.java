package org.shakti.uberreviewservice.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.shakti.uberreviewservice.Models.Booking;
import org.shakti.uberreviewservice.Models.Driver;
import org.shakti.uberreviewservice.Models.Review;
import org.shakti.uberreviewservice.Repositories.BookingRepository;
import org.shakti.uberreviewservice.Repositories.DriverRepository;
import org.shakti.uberreviewservice.Repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ReviewService implements CommandLineRunner {

    private final BookingRepository bookingRepository;
    private final DriverRepository driverRepository;
    private ReviewRepository reviewRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("************************");
//        Review review = Review.builder()
//                .content("Amazing ride quality" + Math.ceil(Math.random() *10))
//                .rating(4.5)
//                .build();
//
//        Driver driver = Driver.builder()
//                .name("shakti")
//                .licenseNumber("12344")
//                .build();
//
//        Booking booking = Booking.builder()
//                .review(review)
//                .driver(driver)
//                .startTime(new Date())
//                .endTime(new Date())
//                .build();
//
//        bookingRepository.save(booking); // auto save the review & driver
//
//        List<Review> reviews = reviewRepository.findAll();
//        for (Review r : reviews) {
//            System.out.println(r.getContent());
//        }

        // N+1 problem solution, when we have some number of driverIds and need to fetch the driver data
        // & their booking, but not eagerly
        List<Long> driverIds = new ArrayList<>(Arrays.asList(1L,3L,4L,5L,6L,7L));
        List<Driver> drivers = driverRepository.findAllByIdIn(driverIds);

        List<Booking> bookings = bookingRepository.findAllByDriverIn(drivers);

//        for(Driver driver : drivers) {
//            List<Booking> bookings = driver.getBookings();
//            bookings.forEach(booking -> System.out.println(booking.getId()));
//        }
    }
}
