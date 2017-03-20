package com.rfgbot.flow.util;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by nickm on 3/20/2017.
 */
public class CollectionUtil {
    public static String toString(Collection<?> collection) {
        return collection.stream().map(Object::toString).collect(Collectors.joining(", "));
    }
}
