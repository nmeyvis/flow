package com.rfgbot.flow;

import java.io.IOException;

/**
 * Simple interface for getting the raw source text that may contain commands
 * Created by nickm on 3/11/2017.
 */
public interface InputProvider {
    String getInput() throws IOException;
}
