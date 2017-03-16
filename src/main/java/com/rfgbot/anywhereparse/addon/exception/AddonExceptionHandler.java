package com.rfgbot.anywhereparse.addon.exception;

/**
 * Handles exceptions that may be thrown during runtime of an addon
 * Created by nickm on 3/14/2017.
 */
public interface AddonExceptionHandler {
    void handle(NoSuchAddonMethodException e);
    void handle(AddonException e);
    void handle(UserInputException e);
}
