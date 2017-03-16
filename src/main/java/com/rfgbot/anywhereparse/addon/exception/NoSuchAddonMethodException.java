package com.rfgbot.anywhereparse.addon.exception;

/**
 * Created by nickm on 3/16/2017.
 */
public class NoSuchAddonMethodException extends AddonException {
    /**
     * the parameter that no method could be found for
     */
    private String param;

    public NoSuchAddonMethodException(String param) {
        super("no method found for parameter: " + param);
        this.param = param;
    }

    public String getParam() {
        return param;
    }
}
