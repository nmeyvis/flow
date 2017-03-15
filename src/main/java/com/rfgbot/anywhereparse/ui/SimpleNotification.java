package com.rfgbot.anywhereparse.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Created by nickm on 3/14/2017.
 */
public class SimpleNotification extends Notification {
    private GridPane grid;
    private Sound sound;

    public SimpleNotification(String message, int lifeMs) {
        this(message, lifeMs, null);
    }

    public SimpleNotification(String message, int lifeMs, Sound sound) {
        super(lifeMs);

        System.out.println("creating simple notification");
        this.sound = sound;

        Text text = new Text(message);
        text.setFill(Color.WHITE);

        grid.add(text, 0, 0);
        grid.setPadding(new Insets(15));

        grid.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

        this.setOnShowing(e -> this.playSound());
    }

    private void playSound() {
        if(sound != null) {
            Media media = new Media(sound.getFile().toURI().toString());
            new MediaPlayer(media).play();
        }
    }

    @Override
    Region region() {
        grid = new GridPane();
        return grid;
    }
}
