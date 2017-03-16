package com.rfgbot.anywhereparse.addon.exception;

/**
 * Should be thrown in response to invalid user input
 * Typically this exception should be displayed to the user in a friendly format
 * Created by nickm on 3/16/2017.
 */
public class UserInputException extends AddonException {
    public UserInputException(Throwable e) {
        super(e);
    }

    public UserInputException(String message) {
        super(message);
    }
}
