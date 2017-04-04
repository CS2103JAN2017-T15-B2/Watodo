package watodo.logic.parser;

import static watodo.commons.core.Messages.MESSAGE_EVENT_TASK_WITHOUT_END_TIME;
import static watodo.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static watodo.logic.parser.CliSyntax.PREFIX_END_TIME;
import static watodo.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static watodo.logic.parser.CliSyntax.PREFIX_START_TIME;
import static watodo.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.NoSuchElementException;

import watodo.commons.exceptions.IllegalValueException;
import watodo.logic.commands.AddCommand;
import watodo.logic.commands.Command;
import watodo.logic.commands.IncorrectCommand;
import watodo.time.parser.ISODateTimeParser;
import watodo.time.parser.StandardDateTimeParser;
import watodo.time.parser.TimeParserSelector;
import watodo.time.parser.TodayTimeParser;
import watodo.time.parser.TomorrowTimeParser;

//@@author A0143873Y
/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     */
    public Command parse(String args) {

        TimeParserSelector timeParserSelector = new TimeParserSelector(
                                                       new TodayTimeParser(),
                                                       new TomorrowTimeParser(),
                                                       new ISODateTimeParser(),
                                                       new StandardDateTimeParser());

        ArgumentTokenizer argsTokenizer =
                new ArgumentTokenizer(PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_PRIORITY, PREFIX_TAG);
        argsTokenizer.tokenize(args);

        //Making priority, startTime and endTime to be optionally null.
        String priority;
        String startTime;
        String endTime;

        try {
            priority = argsTokenizer.getValue(PREFIX_PRIORITY).get();
        } catch (NoSuchElementException nsee) {
            priority = null;
        }

        try {
            startTime = argsTokenizer.getValue(PREFIX_START_TIME).get();
            if (timeParserSelector.canParse(startTime)) {
                startTime = timeParserSelector.delegateTimeParser(startTime);
            }
        } catch (NoSuchElementException nsee) {
            startTime = null;
        }

        try {
            endTime = argsTokenizer.getValue(PREFIX_END_TIME).get();
            if (timeParserSelector.canParse(endTime)) {
                endTime = timeParserSelector.delegateTimeParser(endTime);
            }
        } catch (NoSuchElementException nsee) {
            endTime = null;
        }

        if (endTime == null && startTime != null) {
            return new IncorrectCommand(MESSAGE_EVENT_TASK_WITHOUT_END_TIME);
        }

        try {
            return new AddCommand(
                    argsTokenizer.getPreamble().get(),
                    startTime,
                    endTime,
                    priority,
                    ParserUtil.toSet(argsTokenizer.getAllValues(PREFIX_TAG))
            );
        } catch (NoSuchElementException nsee) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
        //@@author
    }

}
