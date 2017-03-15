package com.rfgbot.anywhereparse.parse;

import com.rfgbot.anywhereparse.Command;

import java.util.List;

/**
 * Created by nickm on 3/12/2017.
 */
public interface CommandParser {
    List<Command> parse(String in);
}
