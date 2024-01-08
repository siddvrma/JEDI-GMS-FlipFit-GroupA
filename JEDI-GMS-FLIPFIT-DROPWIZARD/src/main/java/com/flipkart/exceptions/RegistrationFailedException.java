package com.flipkart.exceptions;

import static com.flipkart.constant.Constants.RED_COLOR;
import static com.flipkart.constant.Constants.RESET_COLOR;

public class RegistrationFailedException extends Exception {
    public RegistrationFailedException(String gymOwnerId) {
        super(RED_COLOR+"Failed to register Gym Owner with id: " + gymOwnerId+RESET_COLOR);
    }
}
