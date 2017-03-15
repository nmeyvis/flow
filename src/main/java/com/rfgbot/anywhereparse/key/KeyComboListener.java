package com.rfgbot.anywhereparse.key;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nickm on 3/11/2017.
 */
public class KeyComboListener implements NativeKeyListener {
    private KeyCombo keyCombo;
    private Runnable callback;

    private Set<Integer> currentlyPressed = new HashSet<>();

    public KeyComboListener(KeyCombo keyCombo, Runnable callback) {
        this.keyCombo = keyCombo;
        this.callback = callback;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent keyEvent) {
        currentlyPressed.add(keyEvent.getKeyCode());
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent keyEvent) {
        if(keyCombo.match(currentlyPressed)) {
            callback.run();
        }

        currentlyPressed.remove(keyEvent.getKeyCode());
    }
}
