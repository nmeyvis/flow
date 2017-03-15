package com.rfgbot.anywhereparse.addon;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by nickm on 3/12/2017.
 */
public interface AddonReflectionExecutor {
    Object exe(Addon addon, List<String> parameters) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
}
