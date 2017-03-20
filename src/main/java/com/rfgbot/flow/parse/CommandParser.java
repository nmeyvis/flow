package com.rfgbot.flow.parse;

import com.rfgbot.flow.Command;

import java.util.List;

/**
 * Created by nickm on 3/12/2017.
 */
public interface CommandParser {
    /**
     * Parse and return a list of commands found in the provided string
     * @param in the string to parse
     * @return the list of commands found
     */
    List<Command> parse(String in);
}
