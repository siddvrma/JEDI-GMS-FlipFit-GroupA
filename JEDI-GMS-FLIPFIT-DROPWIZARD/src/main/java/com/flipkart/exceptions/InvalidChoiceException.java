package com.flipkart.exceptions;

import java.io.InvalidObjectException;

import static com.flipkart.constant.Constants.RED_COLOR;
import static com.flipkart.constant.Constants.RESET_COLOR;

public class InvalidChoiceException extends InvalidObjectException {
    public InvalidChoiceException(){
        super(RED_COLOR+"Please enter valid choice"+RESET_COLOR);
    }
}
