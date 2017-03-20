package com.rfgbot.flow;

import com.rfgbot.flow.addon.exception.AddonException;
import com.rfgbot.flow.addon.exception.AddonExceptionHandler;
import com.rfgbot.flow.addon.exception.NoSuchAddonMethodException;
import com.rfgbot.flow.addon.exception.UserInputException;
import com.rfgbot.flow.ui.ApplicationFX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nickm on 3/14/2017.
 */
public class JavaFXAddonExeErrorHandler implements AddonExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(JavaFXAddonExeErrorHandler.class);

    private ApplicationFX app;

    public JavaFXAddonExeErrorHandler(ApplicationFX app) {
        this.app = app;
    }

    @Override
    public void handle(NoSuchAddonMethodException e) {
        LOG.warn("", e);
        app.displayErrorNotification("Cannot execute addon, no method found for parameter: " + e.getParam());
    }

    @Override
    public void handle(AddonException e) {
        LOG.error("", e);
        app.displayErrorNotification("A fatal addon exception occured for "
                + e.getSourceAddon().getName() +", check logs");
    }

    @Override
    public void handle(UserInputException e) {
        LOG.info("", e);
        app.displayErrorNotification(e.getMessage());
    }
}
