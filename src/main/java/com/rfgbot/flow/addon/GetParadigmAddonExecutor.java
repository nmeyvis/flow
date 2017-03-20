package com.rfgbot.flow.addon;

import com.rfgbot.flow.addon.exception.NoSuchAddonMethodException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * An addon executor that uses reflection to run addon functions.
 * An addon method must follow the following convention:
 *
 * Object get<parameter>();
 * Object get(String str);
 *
 * For example, if the template looks like this:
 *
 * $contacts:bob:cell
 *
 * the code should look something like this:
 *
 * contacts.get("bob").getCell();
 *
 * Created by nickm on 3/11/2017.
 */
public class GetParadigmAddonExecutor implements AddonReflectionExecutor {

    public Object exe(Addon addon, List<String> parameters) throws InvocationTargetException, IllegalAccessException {
        Object obj = addon;

        for(String param : parameters) {
            obj = get(obj, param);
        }

        return obj;
    }

    private Object get(Object obj, String param) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = obj.getClass();

        Method method;

        try
        {
            method = clazz.getMethod("get" + capitalize(param));
        }
        catch (NoSuchMethodException e) {
            try
            {
                method = clazz.getMethod("get", String.class);
            }
            catch(NoSuchMethodException e2) {
                throw new NoSuchAddonMethodException(param);
            }
        }

        return method.invoke(obj, param);
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
