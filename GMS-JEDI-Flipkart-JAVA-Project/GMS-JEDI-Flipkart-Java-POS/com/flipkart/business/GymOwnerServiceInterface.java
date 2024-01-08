package com.flipkart.business;

import com.flipkart.bean.GymOwner;

import java.util.List;

/**
 * This interface defines the service operations related to Gym Owner functionality.
 * It includes methods for requesting gym owner approval, viewing all gym owners,
 * logging in gym owners, and registering new gym owners.
 */
public interface GymOwnerServiceInterface {

    /**
     * Sends a request for approval for a specific gym owner.
     *
     * @param gymOwnerId The ID of the gym owner for which the approval request is sent
     */
    void requestGymOwnerApproval(String gymOwnerId);

    /**
     * Retrieves details of all registered gym owners.
     *
     * @return List of gym owners
     */
    List<GymOwner> viewAllGymOwners();

    /**
     * Validates the login credentials of a gym owner.
     *
     * @param userId   The ID of the gym owner
     * @param password The password entered by the gym owner
     * @return True if the login is successful, false otherwise
     */
    boolean loginGymOwner(String userId, String password);

    /**
     * Registers a new gym owner with the provided details.
     *
     * @param userId     The ID of the gym owner
     * @param userName   The username chosen by the gym owner
     * @param password   The password chosen by the gym owner
     * @param email      The email address of the gym owner
     * @param panNumber  The PAN number of the gym owner
     * @param cardNumber The card number associated with the gym owner
     */
    void registerGymOwner(String userId, String userName, String password, String email, String panNumber, String cardNumber);
}
