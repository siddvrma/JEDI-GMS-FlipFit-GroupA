package com.flipkart.DAO;

import com.flipkart.DAO.BookingInterfaceDAO;
import com.flipkart.bean.Booking;
import com.flipkart.connection.DBConnection;
import com.flipkart.exceptions.BookingFailedException;
import com.flipkart.utils.UserPlan;

import java.sql.*;
import java.time.LocalTime;
import java.util.*;
import java.util.Date;

public class BookingDAO implements BookingInterfaceDAO {
    private static final String INSERT_BOOKING = "INSERT INTO Booking (bookingID, userID, scheduleID) VALUES (?, ?, ?)";
    private static final String SELECT_BOOKINGS_BY_USER_ID = "SELECT * FROM Booking WHERE userID = ?";
    private static final String DELETE_BOOKING_BY_ID = "DELETE FROM Booking WHERE bookingID = ?";
    private static final String SELECT_BOOKING_BY_ID = "SELECT * FROM Booking WHERE bookingID = ?";

    private static List<Booking> allBookingList = new ArrayList<>();
    private static Map<String, List<String>> bookingDetails = new HashMap<>();
    private ScheduleDAO scheduleDAO = new ScheduleDAO();

    public void addBooking(String userName, String scheduleID) throws BookingFailedException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKING)) {

            // Assuming you have a method to generate a unique booking ID
            String bookingId = generateUniqueBookingId(userName, scheduleID);

            preparedStatement.setString(1, bookingId);
            preparedStatement.setString(2, userName);
            preparedStatement.setString(3, scheduleID);

            preparedStatement.executeUpdate();

            // Create a Booking object
            Booking booking = new Booking(bookingId, userName, scheduleID);

            // Add the Booking object to the list
            allBookingList.add(booking);

            // Add booking details to the map
            bookingDetails.computeIfAbsent(userName, k -> new ArrayList<>()).add(scheduleID);

        } catch (SQLException exp) {
            throw new BookingFailedException("Booking failed for current slot. Try again later.");
        }
    }

    public List<Booking> getBookingByCustomerId(String customerId) throws BookingFailedException {
        List<Booking> customerBookings = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKINGS_BY_USER_ID)) {

            preparedStatement.setString(1, customerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String bookingId = resultSet.getString("bookingID");
                    String scheduleId = resultSet.getString("scheduleID");

                    Booking booking = new Booking(bookingId, customerId, scheduleId);
                    customerBookings.add(booking);
                }
            }

        } catch (SQLException e) {
            throw new BookingFailedException("Failed to fetch bookings for customer: " + customerId);
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
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKING_BY_ID)) {

            preparedStatement.setString(1, bookingID);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                throw new BookingFailedException("Could not cancel booking for BookingId: " + bookingID);
            }

            Booking bookingToRemove = null;
            for (Booking booking : allBookingList) {
                if (booking.getBookingID().equals(bookingID)) {
                    bookingToRemove = booking;
                    break;
                }
            }

            if (bookingToRemove != null) {
                allBookingList.remove(bookingToRemove);
            }

        } catch (SQLException e) {
            throw new BookingFailedException("Could not cancel booking for BookingId: " + bookingID);
        }
    }

    public Booking getBookingByBookingId(String bookingId) throws BookingFailedException {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKING_BY_ID)) {

            preparedStatement.setString(1, bookingId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String customerId = resultSet.getString("userID");
                    String scheduleId = resultSet.getString("scheduleID");

                    return new Booking(bookingId, customerId, scheduleId);
                }
            }

        } catch (SQLException e) {
            throw new BookingFailedException("Could not fetch booking by bookingId: " + bookingId);
        }

        throw new BookingFailedException("Booking not found for BookingId: " + bookingId);
    }

    private String generateUniqueBookingId(String userName, String scheduleID) {
        return userName + scheduleID;
    }
}
