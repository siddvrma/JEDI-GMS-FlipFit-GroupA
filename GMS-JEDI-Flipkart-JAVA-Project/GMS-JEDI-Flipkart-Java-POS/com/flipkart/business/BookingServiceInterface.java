package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.utils.UserPlan;

import java.util.Date;
import java.util.List;

/**
 * This interface defines the service operations related to booking functionality.
 * It includes methods for checking booking overlap, adding bookings, canceling bookings,
 * retrieving bookings by customer ID, and getting customer plans.
 */
public interface BookingServiceInterface {

    /**
     * Checks if a booking overlap exists for a customer on a specific date and slot.
     *
     * @param customerId The ID of the customer
     * @param date       The date for the booking
     * @param slotId     The ID of the slot for the booking
     * @return True if there is an overlap, false otherwise
     */
    boolean checkBookingOverlap(String customerId, Date date, String slotId);

    /**
     * Adds a booking for a user to a specific schedule.
     *
     * @param userId     The ID of the user making the booking
     * @param scheduleID The ID of the schedule for which the booking is made
     */
    void addBooking(String userId, String scheduleID);

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
    void cancelBooking(String bookingID);

    /**
     * Retrieves a list of user plans associated with a specific customer ID.
     *
     * @param customerId The ID of the customer for whom plans are retrieved
     * @return List of user plans for the customer
     */
    List<UserPlan> getCustomerPlan(String customerId);
}
