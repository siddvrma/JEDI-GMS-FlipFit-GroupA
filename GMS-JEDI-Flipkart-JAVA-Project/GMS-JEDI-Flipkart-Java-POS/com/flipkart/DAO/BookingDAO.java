package com.flipkart.DAO;

import com.flipkart.bean.Booking;
import com.flipkart.exceptions.BookingFailedException;
import com.flipkart.utils.UserPlan;

import java.time.LocalTime;
import java.util.*;

public class BookingDAO implements BookingInterfaceDAO {

    private List<Booking> allBookingList = new ArrayList<>();
    private Map<String, List<String>> bookingDetails = new HashMap<>();

    ScheduleDAO scheduleDAO = new ScheduleDAO();

    public void addBooking(String userName, String scheduleID) throws BookingFailedException {
        try {
            // Assuming you have a method to generate a unique booking ID
            String bookingId = generateUniqueBookingId(userName, scheduleID);
            // Create a Booking object
            Booking booking = new Booking(bookingId, userName, scheduleID);

            // Add the Booking object to the list
            allBookingList.add(booking);

            // Add booking details to the map
            bookingDetails.computeIfAbsent(userName, k -> new ArrayList<>()).add(scheduleID);
        } catch (Exception exp) {
            throw new BookingFailedException("Booking failed for current slot. Try again later.");
        }
    }

    public List<Booking> getBookingByCustomerId(String customerId) throws BookingFailedException {
        List<Booking> customerBookings = new ArrayList<>();
        for (Booking booking : allBookingList) {
            if (booking.getUserID().equals(customerId)) {
                customerBookings.add(booking);
            }
        }
        return customerBookings;
    }

    public List<UserPlan> getCustomerPlan(String customerId) {
        List<UserPlan> allUserPlan = new ArrayList<>();
        List<Booking> customerBookings = getBookingByCustomerId(customerId);

        for (Booking booking : customerBookings) {
            // Add logic to retrieve UserPlan based on the local data structures
        }

        return allUserPlan;
    }

    public boolean checkBookingOverlap(String customerId, Date date, LocalTime localTime) {
        LocalTime endTime = localTime.plusHours(1);

        List<UserPlan> allUserPlan = getCustomerPlan(customerId);
        for(UserPlan userPlan:allUserPlan){

            if(userPlan.getDate().equals(date)){
                if(localTime.compareTo(userPlan.getTime())<=0  && endTime.compareTo(userPlan.getTime())>=0){
                    return true;
                }
                else if(userPlan.getTime().compareTo(localTime)<=0  && userPlan.getTime().compareTo(endTime)>=0)
                    return true;
            }
        }
        return false;
    }

    public void cancelBookingById(String bookingID) throws BookingFailedException {
        Booking bookingToRemove = null;
        for (Booking booking : allBookingList) {
            if (booking.getBookingID().equals(bookingID)) {
                bookingToRemove = booking;
                break;
            }
        }

        if (bookingToRemove != null) {
            allBookingList.remove(bookingToRemove);
        } else {
            throw new BookingFailedException("Could not cancel booking for BookingId: " + bookingID);
        }
    }

    public Booking getBookingByBookingId(String bookingId) throws BookingFailedException {
        for (Booking booking : allBookingList) {
            if (booking.getBookingID().equals(bookingId)) {
                return booking;
            }
        }
        throw new BookingFailedException("Could not fetch booking by bookingId: " + bookingId);
    }

    private String generateUniqueBookingId(String userName, String scheduleID) {
        // Add logic to generate a unique booking ID
        return userName + scheduleID;
    }
}
