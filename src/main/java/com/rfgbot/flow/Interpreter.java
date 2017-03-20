package com.rfgbot.flow;

import com.rfgbot.flow.addon.Addon;
import com.rfgbot.flow.addon.AddonReflectionExecutor;
import com.rfgbot.flow.addon.AddonRegistry;
import com.rfgbot.flow.addon.exception.AddonException;
import com.rfgbot.flow.addon.exception.AddonExceptionHandler;
import com.rfgbot.flow.addon.exception.NoSuchAddonMethodException;
import com.rfgbot.flow.addon.exception.UserInputException;
import com.rfgbot.flow.parse.CommandParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by nickm on 3/12/2017.
 */
public class Interpreter {
    private static final Logger LOG = LoggerFactory.getLogger(Integer.class);

    private AddonRegistry addonRegistry;
    private CommandParser parser;
    private AddonReflectionExecutor executor;
    private AddonExceptionHandler errorHandler;

    public Interpreter(AddonRegistry addonRegistry, CommandParser parser,
                       AddonReflectionExecutor executor, AddonExceptionHandler errorHandler) {
        this.addonRegistry = addonRegistry;
        this.parser = parser;
        this.executor = executor;
        this.errorHandler = errorHandler;
    }

    /**
     * Interprets the given string for any commands, executes them, and returns a modified string containing
     * the command results, maintains any non command characters
     * @param src string to interpret
     * @return modified source string with command results
     */
    public String interpret(String src) {
        List<Command> commands = parser.parse(src);

        for(Command cmd : commands) {
            Addon addon = addonRegistry.find(cmd.getAlias());

            if(addon != null) {
                try
                {
                    Object result = executor.exe(addon, cmd.getParameters());

                    if(result == null) {
                        throw new AddonException("addon result cannot be null");
                    }

                    src = replace(src, cmd, result.toString());

                } catch(Exception e) {
                    handleAddonException(addon, cmd, e);
                }
            } else {
                errorHandler.handle(new UserInputException("no addon for " + cmd.getAlias()));
            }
        }

        return src;
    }

    private void handleAddonException(Addon addon, Command cmd, Exception e) {
        AddonException addonException;

        if (e instanceof InvocationTargetException) {
            if (e.getCause() instanceof AddonException) {
                addonException = (AddonException) e.getCause();
            } else {
                addonException = new AddonException(e.getCause());
            }
        } else if(e instanceof AddonException) {
            addonException = (AddonException) e;
        } else {
            addonException = new AddonException(e);
        }

        addonException.set(addon, cmd);

        if(addonException instanceof NoSuchAddonMethodException) {
            errorHandler.handle((NoSuchAddonMethodException) addonException);
        } else if(addonException instanceof UserInputException) {
            errorHandler.handle((UserInputException) addonException);
        } else {
            errorHandler.handle(addonException);
        }
    }

    /**
     * Replaces the raw command with the command result into the source string
     * @param src source containing the command
     * @param command the command
     * @param result the result
     * @return the modified string containing the command result
     */
    private String replace(String src, Command command, String result) {
        String before = src.substring(0, command.getStart());
        String after = "";

        // needs to add 1 to the end index to not include the command (getEnd is inclusive)
        // however it cannot add 1 if the command is at the end otherwise out of range error
        if(!command.atEnd(src)) {
            after = src.substring(command.getEnd() + 1, src.length());
        }
        return before + result + after;
    }
}
