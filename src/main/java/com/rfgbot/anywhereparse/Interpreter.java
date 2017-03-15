package com.rfgbot.anywhereparse;

import com.rfgbot.anywhereparse.addon.*;
import com.rfgbot.anywhereparse.parse.CommandParser;
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
    private AddonExecutorErrorHandler errorHandler;

    public Interpreter(AddonRegistry addonRegistry, CommandParser parser, AddonReflectionExecutor executor, AddonExecutorErrorHandler errorHandler) {
        this.addonRegistry = addonRegistry;
        this.parser = parser;
        this.executor = executor;
        this.errorHandler = errorHandler;
    }

    public String interpret(String str) {
        List<Command> commands = parser.parse(str);
        LOG.debug("commands interpreted: {}", commands.size());

        for(Command cmd : commands) {
            Addon addon = addonRegistry.find(cmd.getAlias());
            LOG.debug("addon found? {}", addon != null);
            if(addon != null) {
                try {
                    Object result = executor.exe(addon, cmd.getParameters());

                    if(result == null) {
                        throw new NullPointerException("addon result cannot be null");
                    }

                    str = replace(str, cmd, result.toString());

                } catch (NoSuchMethodException e) {
                    errorHandler.handle(e);
                } catch (InvocationTargetException e) {

                    if(e.getCause() instanceof AddonException) {
                        AddonException addonException = (AddonException) e.getCause();
                        addonException.setSource(addon);
                        errorHandler.handle(addonException);
                    } else {
                        errorHandler.handle(new RuntimeException(e));
                    }
                } catch (IllegalAccessException e) {
                    errorHandler.handle(new RuntimeException(e));
                }
            }
        }

        return str;
    }

    /*
        When we insert, we have to right shift the characters if the command result is longer than the command raw
        When we insert, we have to remove characters if the command result is shorter than command raw
     */
    private String replace(String src, Command command, String result) {
        String before = src.substring(0, command.getStart());
        String after = "";

        // needs to add 1 to the end index to not include the command, however it cannot at 1 if the command
        // is at the end otherwise out of range error
        if(!command.atEnd(src)) {
            after = src.substring(command.getEnd() + 1, src.length());
        }
        return before + result + after;
    }
}
