package watodo.time.parser;

import java.util.ArrayList;

//@@author A0143873Y
/**
 * Assigns time parsers based on user input.
 */
public class TimeParserSelector {
    private ArrayList<TimeParser> parserList;

    public TimeParserSelector(TimeParser... parsers) {
        parserList = new ArrayList<>();
        for (TimeParser parser: parsers){
            parserList.add(parser);
        }
    }

    /**
     * Check if the manager can parse some string
     * @param userInput The string to check
     * @return {@code true} if and only if at least one of the parsers can parse {@code userInput}
     */
    public boolean canParse(String userInput) {
        for (TimeParser parser: parserList) {
            if (parser.applyTo(userInput)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Assigns the appropriate time parser to the user input.
     */
    public String delegateTimeParser(String userInput) {
        for (TimeParser parser: parserList) {
            if (parser.applyTo(userInput)) {
                return parser.parse(userInput);
            }
        }
        return null;
    }
}
