package com.rfgbot.anywhereparse.addon.exception;

import com.rfgbot.anywhereparse.Command;
import com.rfgbot.anywhereparse.addon.Addon;

/**
 * General exception that encapsulates any exception thrown during the execution of an addon
 * Created by nickm on 3/14/2017.
 */
public class AddonException extends RuntimeException {
    private Addon sourceAddon;
    private Command sourceCmd;

    public AddonException(Throwable e) {
        super(e);
    }

    public AddonException(String message) {
        super(message);
    }

    public void set(Addon addon, Command cmd) {
        setSourceAddon(addon);
        setSourceCmd(cmd);
    }

    public Addon getSourceAddon() {
        return sourceAddon;
    }

    public void setSourceAddon(Addon sourceAddon) {
        this.sourceAddon = sourceAddon;
    }

    public Command getSourceCmd() {
        return sourceCmd;
    }

    public void setSourceCmd(Command sourceCmd) {
        this.sourceCmd = sourceCmd;
    }
}
