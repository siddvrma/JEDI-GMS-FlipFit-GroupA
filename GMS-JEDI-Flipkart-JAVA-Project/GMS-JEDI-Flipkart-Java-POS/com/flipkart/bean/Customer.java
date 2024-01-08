package com.flipkart.bean;

/**
 * Represents a customer in the system, extending the base User class.
 */
public class Customer extends User {

    /**
     * The phone number associated with the customer.
     */
    private String customerPhone;

    /**
     * The card details associated with the customer.
     */
    private String cardDetails;

    /**
     * Default constructor for the Customer class.
     */
    public Customer() {
    }

    /**
     * Constructs a new customer with the given details.
     *
     * @param userId         The unique identifier for the customer.
     * @param userName       The username chosen by the customer.
     * @param email          The email address of the customer.
     * @param password       The password chosen by the customer.
     * @param customerPhone  The phone number associated with the customer.
     * @param cardDetails    The card details associated with the customer.
     */
    public Customer(String userId, String userName, String email, String password, String customerPhone, String cardDetails) {
        super(userId, userName, email, password, Role.CUSTOMER);
        this.customerPhone = customerPhone;
        this.cardDetails = cardDetails;
    }

    /**
     * Gets the phone number associated with the customer.
     *
     * @return The customer's phone number.
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Sets the phone number associated with the customer.
     *
     * @param customerPhone The customer's phone number.
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * Gets the card details associated with the customer.
     *
     * @return The customer's card details.
     */
    public String getCardDetails() {
        return cardDetails;
    }

    /**
     * Sets the card details associated with the customer.
     *
     * @param cardDetails The customer's card details.
     */
    public void setCardDetails(String cardDetails) {
        this.cardDetails = cardDetails;
    }

    /**
     * Overrides the toString method to provide a string representation of the customer.
     *
     * @return A string representation of the customer.
     */
    @Override
    public String toString() {
        return super.toString() +
                "customerPhone='" + customerPhone + '\'' +
                ", cardDetails='" + cardDetails;
    }
}
