package com.rfgbot.anywhereparse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickm on 3/11/2017.
 */
public class Command {
    private String alias;
    private List<String> parameters;
    private int start;
    private int end;

    public Command() {
        parameters = new ArrayList<>();
    }

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
}
