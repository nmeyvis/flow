package com.rfgbot.anywhereparse.addon;

/**
 * Created by nickm on 3/14/2017.
 */
public class AddonException extends RuntimeException {
    private Addon source;

    public AddonException(RuntimeException e) {
        super(e);
    }

    public AddonException(String message) {
        super(message);
    }

    public Addon getSource() {
        return source;
    }

    public void setSource(Addon source) {
        this.source = source;
    }
}
