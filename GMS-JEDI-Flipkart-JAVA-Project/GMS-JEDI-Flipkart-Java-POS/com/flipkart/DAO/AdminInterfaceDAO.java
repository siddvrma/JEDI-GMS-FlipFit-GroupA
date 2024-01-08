package com.flipkart.DAO;

import com.flipkart.bean.GymCentre;
import com.flipkart.bean.GymOwner;

import java.util.List;

/**
 * This interface defines the data access operations related to admin functionality
 * for managing Gym Owners and Gym Centres.
 */
public interface AdminInterfaceDAO {

    /**
     * Retrieves a list of pending Gym Owners awaiting approval.
     *
     * @return List of pending Gym Owners
     */
    List<GymOwner> getPendingGymOwners();

    /**
     * Validates a Gym Owner based on the provided ID and approval status.
     *
     * @param gymOwnerId The ID of the Gym Owner to be validated
     * @param isApproved The approval status (1 for approved, 0 for not approved)
     */
    void validateGymOwner(String gymOwnerId, int isApproved);

    /**
     * Validates a Gym Centre based on the provided ID and approval status.
     *
     * @param gymCentreId The ID of the Gym Centre to be validated
     * @param isApproved  The approval status (1 for approved, 0 for not approved)
     */
    void validateGymCentre(String gymCentreId, int isApproved);

    /**
     * Retrieves a list of pending Gym Centres awaiting approval.
     *
     * @return List of pending Gym Centres
     */
    List<GymCentre> getPendingGymCentres();
}
