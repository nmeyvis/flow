package com.rfgbot.flow.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Created by nickm on 3/14/2017.
 */
public class ApplicationFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform.setImplicitExit(false);
    }

    public void launchIt(String... args) {
        ApplicationFX.launch(args);
    }

    public void displayErrorNotification(String message) {
        displayNotification(message, Sound.ERROR);
    }

    public void displayNotification(String message, Sound sound) {
        Platform.runLater(() -> new SimpleNotification(message, 3500, sound).show());
    }
}
