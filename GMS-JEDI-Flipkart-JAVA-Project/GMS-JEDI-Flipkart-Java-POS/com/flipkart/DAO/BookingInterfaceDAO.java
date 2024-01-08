package com.flipkart.DAO;

import com.flipkart.bean.Booking;

import java.util.List;

/**
 * This interface defines the data access operations related to booking functionality.
 * It includes methods for adding bookings, retrieving bookings by customer ID, and canceling bookings.
 */
public interface BookingInterfaceDAO {

    /**
     * Adds a booking for a user to a specific schedule.
     *
     * @param userName   The name of the user making the booking
     * @param scheduleID The ID of the schedule for which the booking is made
     */
    void addBooking(String userName, String scheduleID);

    /**
     * Retrieves a list of bookings associated with a specific customer ID.
     *
     * @param customerId The ID of the customer for whom bookings are retrieved
     * @return List of bookings made by the customer
     */
    List<Booking> getBookingByCustomerId(String customerId);

    /**
     * Cancels a booking based on the provided booking ID.
     *
     * @param bookingID The ID of the booking to be canceled
     */
    void cancelBookingById(String bookingID);
}
