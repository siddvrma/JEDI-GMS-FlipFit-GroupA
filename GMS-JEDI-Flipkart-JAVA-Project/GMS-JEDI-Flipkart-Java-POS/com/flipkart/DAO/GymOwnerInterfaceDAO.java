package com.flipkart.DAO;

import com.flipkart.bean.GymOwner;

import java.util.List;

/**
 * This interface defines the data access operations related to Gym Owner functionality.
 * It includes methods for retrieving gym owner lists, registering new gym owners,
 * managing approval status, and handling approval requests.
 */
public interface GymOwnerInterfaceDAO {

    /**
     * Retrieves a list of all registered gym owners.
     *
     * @return List of gym owners
     */
    List<GymOwner> getGymOwnerList();

    /**
     * Sets the list of gym owners.
     *
     * @param gymOwnerList The list of gym owners to be set
     */
    void setGymOwnerList(List<GymOwner> gymOwnerList);

    /**
     * Registers a new gym owner.
     *
     * @param gymOwner The GymOwner object representing the details of the new gym owner
     */
    void registerGymOwner(GymOwner gymOwner);

    /**
     * Retrieves a list of pending gym owners awaiting approval.
     *
     * @return List of pending gym owners
     */
    List<GymOwner> getPendingGymOwnerList();

    /**
     * Sends a request for approval for a specific gym owner.
     *
     * @param gymOwnerId The ID of the gym owner for which the approval request is sent
     */
    void sendOwnerApprovalRequest(String gymOwnerId);

    /**
     * Sets the list of pending gym owners.
     */
    void setPendingGymOwnerList();

    /**
     * Validates a gym owner based on the provided owner ID and approval status.
     *
     * @param gymOwnerId The ID of the gym owner to be validated
     * @param isApproved  The approval status (1 for approved, 0 for not approved)
     */
    void validateGymOwner(String gymOwnerId, int isApproved);
}
