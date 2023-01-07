package co.com.sofka.util;

import co.com.sofka.models.Booking;
import co.com.sofka.models.Bookingdates;
import com.github.javafaker.Faker;

public class CreateBooking {
    Faker faker = new Faker();
    private static Booking booking = new Booking();
    private static Bookingdates bookingdates = new Bookingdates();


    public Booking create(){
        return booking.createBooking(
                faker.artist().name(),
                faker.name().lastName(),
                100,
                true,
                bookingdates.create("2018-01-01", "2018-01-01"),
                "Breakfast"
        );
    }
}
