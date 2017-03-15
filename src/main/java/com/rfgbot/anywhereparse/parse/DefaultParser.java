package com.rfgbot.anywhereparse.parse;

import com.rfgbot.anywhereparse.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickm on 3/12/2017.
 */
public class DefaultParser implements CommandParser {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultParser.class);

    @Override
    public List<Command> parse(String in) {
        LOG.debug("parsing: {}", in);
        List<Command> commands = new ArrayList<>();
        char[] rChars = in.toCharArray();

        for(int i = 0; i < rChars.length;) {
            char charr = rChars[i];

            if(charr == '$') {
                List<String> params = new ArrayList<>();

                int traversed = getParameters(params, rChars, i + 1);
                int moved = i + traversed;

                commands.add(new Command(params.get(0), params.subList(1, params.size()), i, moved));
                i += moved;
                continue;
            }

            i++;
        }

        return commands;
    }

    /**
     *
     * @param params list to fill
     * @param chars source array
     * @param start start in array
     * @return total str length of param plus terminator
     */
    private int getParameters(List<String> params, char[] chars, int start) {
        int count = 0;
        Character termUsed;

        do {
            StringBuilder param = new StringBuilder();
            termUsed = appendUntil(param, chars, start + count, ':', '|');

            count += param.length();

            if(termUsed != null) { // +1 count the terminator unless there was none (reached the end)
                count++;
            }
            params.add(param.toString());
        }
        while(!(termUsed == null || termUsed == '|' || termUsed == ' ')); // end of command

        return count;
    }

    private Character appendUntil(StringBuilder builder, char[] source, int start, char... terminators) {
        for(int i = start; i < source.length; i++) {
            char charr = source[i];

            for(char term : terminators) {
                if(term == charr) {
                    return term;
                }
            }

            builder.append(charr);
        }

        return null;
    }
}
