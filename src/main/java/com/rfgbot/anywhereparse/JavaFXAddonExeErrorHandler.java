package com.rfgbot.anywhereparse;

import com.rfgbot.anywhereparse.addon.AddonException;
import com.rfgbot.anywhereparse.addon.AddonExecutorErrorHandler;
import com.rfgbot.anywhereparse.ui.ApplicationFX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nickm on 3/14/2017.
 */
public class JavaFXAddonExeErrorHandler implements AddonExecutorErrorHandler {

    private static final Logger LOG = LoggerFactory.getLogger(JavaFXAddonExeErrorHandler.class);

    private ApplicationFX app;

    public JavaFXAddonExeErrorHandler(ApplicationFX app) {
        this.app = app;
    }

    @Override
    public void handle(NoSuchMethodException e) {
        LOG.error("no method", e);
        app.displayNotification("unable to find method");
    }

    @Override
    public void handle(RuntimeException e) {
        LOG.error("runtime error during addon execution", e);
        app.displayNotification("fatal error has occured, check logs");
    }

    @Override
    public void handle(AddonException e) {
        LOG.error("", e);
        app.displayNotification(e.getMessage());
    }
}
