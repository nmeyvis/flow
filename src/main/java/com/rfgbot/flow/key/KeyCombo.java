package com.rfgbot.flow.key;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by nickm on 3/11/2017.
 */
public class KeyCombo {

    public static class AWT {
        public static final KeyCombo SELECT_ALL = new KeyCombo(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
        public static final KeyCombo COPY = new KeyCombo(KeyEvent.VK_CONTROL, KeyEvent.VK_C);
        public static final KeyCombo PASTE = new KeyCombo(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
    }

   /* public static class Native {
        public static final KeyCombo SELECT_ALL = new KeyCombo(NativeKeyEvent.VK_CONTROL, NativeKeyEvent.VC_A);
        public static final KeyCombo COPY = new KeyCombo(NativeKeyEvent.VC_CONTROL, NativeKeyEvent.VC_C);
        public static final KeyCombo PASTE = new KeyCombo(NativeKeyEvent.VC_CONTROL, NativeKeyEvent.VC_V);
    }*/


    public static class Native {
        public static final KeyCombo SELECT_ALL = new KeyCombo(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
        public static final KeyCombo COPY = new KeyCombo(KeyEvent.VK_CONTROL, KeyEvent.VK_C);
        public static final KeyCombo PASTE = new KeyCombo(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
    }
    private Set<Integer> keys;

    public KeyCombo(Set<Integer> keys) {
        this.keys = keys;
    }

    public KeyCombo(Integer... keys) {
        this(new TreeSet<>());
        Collections.addAll(this.keys, keys);
    }

    public Set<Integer> getKeys() {
        return keys;
    }

    public boolean match(Set<Integer> keys) {
        return this.keys.equals(keys);
    }

    public void emulate(Robot robot) {
        keys.forEach(robot::keyPress);
        keys.forEach(robot::keyRelease);
    }
}
