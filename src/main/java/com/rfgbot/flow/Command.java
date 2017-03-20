package com.rfgbot.flow;

import java.util.List;

/**
 *
 * Created by nickm on 3/11/2017.
 */
public class Command {
    /**
     * The addon alias that triggered this command
     */
    private String alias;

    /**
     * The list of parameters
     */
    private List<String> parameters;

    /**
     * Index in the source array where this command originated
     */
    private int start;

    /**
     * Index in the source array where this command terminated
     */
    private int end;

    public Command(String alias, List<String> parameters, int start, int end) {
        this.alias = alias;
        this.parameters = parameters;
        this.start = start;
        this.end = end;
    }

    public boolean atEnd(String str) {
        return str.length() == end + 1;
    }

    public String getAlias() {
        return alias;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return alias + "(" + String.join(", ", parameters) + ")@[" + start + ", " + end + "]";
    }
}
