package com.rfgbot.anywhereparse.ui;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 * Created by nickm on 3/14/2017.
 */
public abstract class Notification extends Stage {

    public Notification(int lifeMs) {
        super(StageStyle.TRANSPARENT);

        StageAlignment.WinPosition pos = StageAlignment.getWinPosition(0.15, 0.1,
                StageAlignment.RIGHT, StageAlignment.BOTTOM);

        Scene scene = new Scene(region(), pos.getWidth(), pos.getHeight());
        scene.setFill(Color.TRANSPARENT);

        setAlwaysOnTop(true);
        setX(pos.getX());
        setY(pos.getY());

        this.setScene(scene);

        PauseTransition transition = new PauseTransition(Duration.millis(lifeMs));
        transition.setOnFinished(e -> this.close());
        transition.play();
    }

    abstract Region region();
}
