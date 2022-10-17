package tuthub.logic.parser;

import static tuthub.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tuthub.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import tuthub.logic.commands.FindByModuleCommand;
import tuthub.model.tutor.ModuleContainsKeywordPredicate;

public class FindByModuleCommandParserTest {

    private FindByModuleCommandParser parser = new FindByModuleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindByModuleCommand() {
        // no leading and trailing whitespaces
        FindByModuleCommand expectedFindByModuleCommand =
                new FindByModuleCommand(new ModuleContainsKeywordPredicate(Arrays.asList("cs2100", "cs2105")));
        assertParseSuccess(parser, "cs2100 cs2105", expectedFindByModuleCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n cs2100 \n \t cs2105 \t", expectedFindByModuleCommand);
    }
}