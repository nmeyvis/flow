package com.rfgbot.flow.key;

import com.rfgbot.flow.util.CollectionUtil;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nickm on 3/11/2017.
 */
public class KeyComboListener implements NativeKeyListener {
    private static final Logger LOG = LoggerFactory.getLogger(KeyComboListener.class);

    private KeyCombo keyCombo;
    private Runnable callback;

    private Set<Integer> currentlyPressed = new HashSet<>();

    public KeyComboListener(KeyCombo keyCombo, Runnable callback) {
        this.keyCombo = keyCombo;
        this.callback = callback;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        System.out.println("typed:: " + nativeKeyEvent.getKeyCode());
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent keyEvent) {
        currentlyPressed.add(keyEvent.getKeyCode());
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent keyEvent) {
        LOG.debug("key released: " + keyEvent.getKeyCode());

        if(keyCombo.match(currentlyPressed)) {
            callback.run();
        }

        currentlyPressed.remove(keyEvent.getKeyCode());
        LOG.debug("currently pressed: " + CollectionUtil.toString(currentlyPressed));
    }
}
