package com.rfgbot.flow.parse;

import com.rfgbot.flow.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nickm on 3/12/2017.
 */
public class DefaultParser implements CommandParser {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultParser.class);

    /**
     * The character that indicates the start of a command
     */
    private final char marker;

    /**
     * The character that separates parameters from each other
     */
    private final char delimiter;

    /**
     * The character that indicates the end of a command
     */
    private final char terminator;

    public DefaultParser(char marker, char delimiter, char terminator) {
        this.marker = marker;
        this.delimiter = delimiter;
        this.terminator = terminator;
    }

    @Override
    public List<Command> parse(String in) {
        LOG.debug("parsing: {}", in);
        List<Command> commands = new ArrayList<>();
        char[] rChars = in.toCharArray();

        for(int i = 0; i < rChars.length;) {
            char charr = rChars[i];

            if(charr == marker) {
                List<String> params = new ArrayList<>();

                int traversed = getParameters(params, rChars, i + 1); // add one to exclude marker
                int move = i + traversed;

                // first entry is alias, rest are parameters
                commands.add(new Command(params.get(0), params.subList(1, params.size()), i, move));
                i += move;
            } else {
                i++;
            }
        }

        return commands;
    }

    /**
     * Fill the provided list with parameters that are separated by the delimiter from the provided character array
     * @param params list to fill
     * @param chars source array
     * @param start start in array
     * @return total str length of parameters including any delimiters or terminator
     */
    private int getParameters(List<String> params, char[] chars, int start) {
        int count = 0;
        Character termUsed;

        do {
            StringBuilder param = new StringBuilder();
            termUsed = appendUntil(param, chars, start + count, delimiter, terminator);

            count += param.length();

            if(termUsed != null) { // count the delimiter / terminator unless there was none (reached the end)
                count++;
            }
            params.add(param.toString());
        }
        while(!(termUsed == null || termUsed == terminator)); // while not end of command

        return count;
    }

    /**
     * Appends the characters from the character array into the StringBuilder until it reached one of the
     * terminating characters
     * @param builder the builder to append to
     * @param source the character array to iterate through
     * @param start the position in the source array to start at
     * @param terminators an array of characters, where any one character can stop the iteration
     * @return the character that triggered the stop
     */
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
