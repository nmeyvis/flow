package com.rfgbot.flow.ui.util;

import java.awt.*;

/**
 * Created by nickm on 3/14/2017.
 */
public class ScreenUtil {

    public static Dimension getWindowBounds() {
        Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        return new Dimension((int) bounds.getWidth(), (int) bounds.getHeight());
    }

    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
