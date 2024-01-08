package com.flipkart.bean;

/**
 * Represents a payment made in the system.
 */
public class Payment {

    /**
     * The unique identifier for the payment.
     */
    private String paymentId;

    /**
     * The amount paid for the payment.
     */
    private String amountPaid;

    /**
     * The booking ID associated with the payment.
     */
    private String bookingId;

    /**
     * Gets the unique identifier for the payment.
     *
     * @return The payment ID.
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the unique identifier for the payment.
     *
     * @param paymentId The payment ID.
     */
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    /**
     * Gets the amount paid for the payment.
     *
     * @return The amount paid.
     */
    public String getAmountPaid() {
        return amountPaid;
    }

    /**
     * Sets the amount paid for the payment.
     *
     * @param amountPaid The amount paid.
     */
    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    /**
     * Gets the booking ID associated with the payment.
     *
     * @return The booking ID.
     */
    public String getBookingId() {
        return bookingId;
    }

    /**
     * Sets the booking ID associated with the payment.
     *
     * @param bookingId The booking ID.
     */
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}