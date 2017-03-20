package com.rfgbot.flow.ui;

import com.rfgbot.flow.ui.util.ScreenUtil;

import java.awt.*;

/**
 * Created by nickm on 3/14/2017.
 */
public class StageAlignment {

    public static class WinPosition {
        int width, height, x, y;


        public WinPosition(int width, int height, int x, int y) {
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public static final double BOTTOM = 1;
    public static final double MIDDLE = 0.5;
    public static final double TOP = 0;
    public static final double LEFT = 0;
    public static final double RIGHT =1;

    public static WinPosition getWinPosition(double widthPerc, double heightPerc, double xAlign, double yAlign) {
        return getWinPosition(widthPerc, heightPerc, xAlign, yAlign, true);
    }

    public static WinPosition getWinPosition(double widthPerc, double heightPerc, double xAlign, double yAlign, boolean withinWinBounds) {
        Dimension bounds = (withinWinBounds) ? ScreenUtil.getWindowBounds() : ScreenUtil.getScreenSize();

        int width = (int) (bounds.width * widthPerc);
        int height = (int) (bounds.height * heightPerc);

        int x = (int) (bounds.width * xAlign - (width * xAlign));
        int y = (int) (bounds.height * yAlign - (height * yAlign));

        return new WinPosition(width, height, x, y);
    }
}
