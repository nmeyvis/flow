package com.rfgbot.anywhereparse.ui;


import java.net.URL;

/**
 * Created by nickm on 3/14/2017.
 */
public enum Sound {

    ERROR("error.mp3");

    private final String name;

    Sound(String name) {
        this.name = name;
    }

    public URL getFile() {
        return ClassLoader.getSystemResource("sounds/" + name);
    }
}
