import com.rfgbot.flow.Command;
import com.rfgbot.flow.parse.DefaultParser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * Created by nickm on 3/12/2017.
 */

public class DefaultParserTest {

    private static DefaultParser parser = new DefaultParser('$', ':', '|');

    @Test
    public void testSingleCommand() {
        final String[] testsOneParam = new String[] {
                "$alias:param1",
                "$alias:param1",
                "testing before, $alias:param1",
                "testing before, $alias:param1|",
                "$alias:param1|, testing after",
                "testing before, $alias:param1|, testing after"
        };

        final String[] testsTwoParams = new String[] {
                "$alias:param1:param2",
                "$alias:param1:param2|",
                "testing before, $alias:param1:param2",
                "testing before, $alias:param1:param2|",
                "$alias:param1:param2|, testing after",
                "testing before, $alias:param1:param2|, testing after"
        };

        for(String test : testsOneParam) {
            assertSingleParam(test);
            assertSingleParam(test.replace(" ", "")); // test with spaces removed
        }

        for(String test : testsTwoParams) {
            assertTwoParams(test);
            assertTwoParams(test.replace(" ", "")); // test with spaces removed
        }
    }

    private void assertSingleParam(String input) {
        List<Command> result = parser.parse(input);
        Command cmd = result.get(0);
        assertHas(cmd, "alias", "param1");
    }

    private void assertTwoParams(String input) {
        List<Command> result = parser.parse(input);
        Command cmd = result.get(0);
        assertHas(cmd, "alias", "param1", "param2");
    }

    private void assertHas(Command cmd, String alias, String... parameters) {
        assertEquals(cmd.getAlias(), alias);
        for(int i = 0; i < parameters.length; i++) {
            assertEquals(parameters[i], cmd.getParameters().get(i));
        }
    }
}
