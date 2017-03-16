package com.rfgbot.anywhereparse;

import com.rfgbot.anywhereparse.addon.exception.AddonException;
import com.rfgbot.anywhereparse.addon.exception.AddonExceptionHandler;
import com.rfgbot.anywhereparse.addon.exception.NoSuchAddonMethodException;
import com.rfgbot.anywhereparse.addon.exception.UserInputException;
import com.rfgbot.anywhereparse.ui.ApplicationFX;
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
