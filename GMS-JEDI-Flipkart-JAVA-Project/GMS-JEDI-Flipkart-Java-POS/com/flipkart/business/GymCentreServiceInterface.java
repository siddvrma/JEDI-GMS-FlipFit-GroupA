package com.flipkart.business;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.Slot;

import java.sql.Date;
import java.util.List;

/**
 * This interface defines the service operations related to Gym Centre functionality.
 * It includes methods for retrieving gym centers by owner ID and city, getting available
 * slots by center and date, adding a new gym center, requesting gym center approval,
 * and retrieving gym center details by ID.
 */
public interface GymCentreServiceInterface {

    /**
     * Retrieves all gym centers associated with a specific owner ID.
     *
     * @param gymOwnerId The ID of the gym owner
     * @return List of gym centers owned by the specified owner
     */
    List<GymCentre> getAllCentresByOwnerId(String gymOwnerId);

    /**
     * Retrieves gym centers based on the specified city.
     *
     * @param city The city for which gym centers are retrieved
     * @return List of gym centers in the specified city
     */
    List<GymCentre> getCentresByCity(String city);

    /**
     * Retrieves available slots for a specific gym center and date.
     *
     * @param centreID The ID of the gym center
     * @param date     The date for which available slots are retrieved
     * @return List of available slots for the specified gym center and date
     */
    List<Slot> getAvailableSlotsByCentreAndDate(String centreID, Date date);

    /**
     * Adds a new gym center to the system.
     *
     * @param gymCentre The GymCentre object representing the details of the new gym center
     */
    void addCenter(GymCentre gymCentre);

    /**
     * Sends a request for approval for a specific gym center.
     *
     * @param gymCentreId The ID of the gym center for which the approval request is sent
     */
    void requestGymCentreApproval(String gymCentreId);

    /**
     * Retrieves details of a specific gym center based on the provided center ID.
     *
     * @param centreID The ID of the gym center
     * @return GymCentre object representing the details of the specified gym center
     */
    GymCentre getGymCentreById(String centreID);
}
