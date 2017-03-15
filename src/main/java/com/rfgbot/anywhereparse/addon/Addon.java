package com.rfgbot.anywhereparse.addon;

/**
 * Created by nickm on 3/11/2017.
 */
public interface Addon {
    String getName();
    String getDescription();
    String[] getAliases();

    void onLoad();
    void onUnload();
}
