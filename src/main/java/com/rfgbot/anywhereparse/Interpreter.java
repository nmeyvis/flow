package com.rfgbot.anywhereparse;

import com.rfgbot.anywhereparse.addon.Addon;
import com.rfgbot.anywhereparse.addon.AddonReflectionExecutor;
import com.rfgbot.anywhereparse.addon.AddonRegistry;
import com.rfgbot.anywhereparse.addon.exception.AddonException;
import com.rfgbot.anywhereparse.addon.exception.AddonExceptionHandler;
import com.rfgbot.anywhereparse.addon.exception.UserInputException;
import com.rfgbot.anywhereparse.parse.CommandParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

                } catch(NoSuchMethodException e) {
                    errorHandler.handle(e);
                } catch(UserInputException e) {
                    e.setSource(addon);
                    errorHandler.handle(e);
                } catch(Exception e) {
                    AddonException addonException = new AddonException(e);
                    addonException.setSource(addon);
                    errorHandler.handle(addonException);
                }
            } else {
                errorHandler.handle(new UserInputException("no addon for " + cmd.getAlias()));
            }
        }

        return src;
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
