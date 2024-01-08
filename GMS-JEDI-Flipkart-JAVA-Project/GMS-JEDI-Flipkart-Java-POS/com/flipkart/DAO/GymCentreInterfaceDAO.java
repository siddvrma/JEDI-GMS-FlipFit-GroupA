package com.flipkart.DAO;

import com.flipkart.bean.GymCentre;

import java.util.List;

/**
 * This interface defines the data access operations related to Gym Centre functionality.
 * It includes methods for retrieving gym centres based on owner ID, centre ID, city,
 * adding a new gym centre, managing approval status, and fetching pending gym centres.
 */
public interface GymCentreInterfaceDAO {

    /**
     * Retrieves a list of all gym centres associated with a specific owner ID.
     *
     * @param gymOwnerId The ID of the gym owner
     * @return List of gym centres owned by the specified owner
     */
    List<GymCentre> getAllCentresByOwnerId(String gymOwnerId);

    /**
     * Retrieves a specific gym centre based on the provided centre ID.
     *
     * @param gymCentreId The ID of the gym centre
     * @return GymCentre object representing the details of the gym centre
     */
    GymCentre getGymCentreByCentreId(String gymCentreId);

    /**
     * Adds a new gym centre to the system.
     *
     * @param centre The GymCentre object representing the details of the new gym centre
     */
    void addGymCentre(GymCentre centre);

    /**
     * Retrieves a list of pending gym centres awaiting approval.
     *
     * @return List of pending gym centres
     */
    List<GymCentre> getPendingGymCentreList();

    /**
     * Validates a gym centre based on the provided centre ID and approval status.
     *
     * @param gymCentreId The ID of the gym centre to be validated
     * @param isApproved  The approval status (1 for approved, 0 for not approved)
     */
    void validateGymCentre(String gymCentreId, int isApproved);

    /**
     * Sends a request for approval for a specific gym centre.
     *
     * @param gymCentreId The ID of the gym centre for which the approval request is sent
     */
    void sendCentreApprovalRequest(String gymCentreId);

    /**
     * Retrieves a list of gym centres based on the specified city.
     *
     * @param city The city for which gym centres are retrieved
     * @return List of gym centres in the specified city
     */
    List<GymCentre> getGymCentreListByCity(String city);
}
