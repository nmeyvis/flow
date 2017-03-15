package com.rfgbot.anywhereparse.addon;

/**
 * Created by nickm on 3/14/2017.
 */
public interface AddonExecutorErrorHandler {
    void handle(NoSuchMethodException e);
    void handle(RuntimeException e);
    void handle(AddonException e);
}
