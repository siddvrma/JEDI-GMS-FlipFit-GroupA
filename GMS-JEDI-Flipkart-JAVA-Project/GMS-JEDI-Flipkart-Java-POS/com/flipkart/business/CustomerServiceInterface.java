package com.flipkart.business;

import com.flipkart.bean.Booking;
import com.flipkart.bean.Customer;
import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;
import com.flipkart.utils.UserPlan;

import java.sql.Date;
import java.util.List;

/**
 * This interface defines the service operations related to customer functionality.
 * It includes methods for retrieving gym center details by city, available slots,
 * customer bookings, booking slots, canceling bookings, registering customers,
 * viewing customer profiles, validating user credentials, and getting customer plans.
 */
public interface CustomerServiceInterface {

    /**
     * Retrieves details of all gym centers in a specific city.
     *
     * @param city The city for which gym center details are retrieved
     * @return List of gym centers in the specified city
     */
    List<GymCentre> getAllGymCenterDetailsByCity(String city);

    /**
     * Retrieves available slots for a specific gym center and date.
     *
     * @param centreID The ID of the gym center
     * @param date     The date for which available slots are retrieved
     * @return List of available slots for the specified gym center and date
     */
    List<Slot> getAvailableSlots(String centreID, Date date);

    /**
     * Retrieves bookings made by a specific customer.
     *
     * @param customerId The ID of the customer
     * @return List of bookings made by the customer
     */
    List<Booking> getCustomerBookings(String customerId);

    /**
     * Books a slot for a customer on a specific date and slot ID at a given gym center.
     *
     * @param userID    The ID of the user making the booking
     * @param date      The date for the booking
     * @param slotId    The ID of the slot for the booking
     * @param centreId  The ID of the gym center for the booking
     * @return True if the booking is successful, false otherwise
     */
    boolean bookSlot(String userID, Date date, String slotId, String centreId);

    /**
     * Cancels a booking based on the provided booking ID.
     *
     * @param bookingID The ID of the booking to be canceled
     */
    void cancelBookingbyID(String bookingID);

    /**
     * Registers a new customer with the provided details.
     *
     * @param userName     The username chosen by the customer
     * @param password     The password chosen by the customer
     * @param email        The email address of the customer
     * @param phoneNumber  The phone number of the customer
     * @param cardNumber   The card number associated with the customer
     */
    void registerCustomer(String userName, String password, String email, String phoneNumber, String cardNumber);

    /**
     * Retrieves the profile details of a specific customer.
     *
     * @param userName The username of the customer
     * @return Customer object representing the profile details of the customer
     */
    Customer viewMyProfile(String userName);

    /**
     * Validates the user's credentials (username and password).
     *
     * @param userName The username entered by the user
     * @param password The password entered by the user
     * @return True if the user is valid, false otherwise
     */
    boolean isUserValid(String userName, String password);

    /**
     * Retrieves a list of user plans associated with a specific customer ID.
     *
     * @param userName The username of the customer
     * @return List of user plans for the customer
     */
    List<UserPlan> getCustomerPlan(String userName);
}
