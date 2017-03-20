package com.rfgbot.flow.util;

/**
 * Created by nickm on 3/12/2017.
 */
public class ArrayUtil {

    public static boolean contains(Object[] array, Object obj) {
        for(Object i : array) {
            if(i.equals(obj)) {
                return true;
            }
        }

        return false;
    }

    public static boolean contains(char[] array, char find) {
        for(char c : array) {
            if(c == find) {
                return true;
            }
        }

        return false;
    }

}
