package watodo.time.parser;

import java.util.ArrayList;

//@@author A0143873Y
/**
 * Assigns time parsers based on user input.
 * Applying Separation of Concerns Principle, this class gathers an ArrayList
 * of TimeParser and help checks which one can parse the given time format before
 * assigning.
 */
public class TimeParserSelector {
    private ArrayList<TimeParser> parserMenu;

    public TimeParserSelector(TimeParser... parsers) {
        parserMenu = new ArrayList<>();
        for (TimeParser parser: parsers) {
            parserMenu.add(parser);
        }
    }

    /**
     * Verify whether user input applies to the format of the respective
     * time parser.
     *
     * @param input
     * @return true if the format fits and false otherwise.
     */
    public boolean canParse(String userInput) {
        for (TimeParser parser: parserMenu) {
            if (parser.applyTo(userInput)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Assigns the appropriate time parser to the user input.
     */
    public String assignTimeParser(String userInput) {
        for (TimeParser parser: parserMenu) {
            if (parser.applyTo(userInput)) {
                return parser.parse(userInput);
            }
        }
        return null;
    }
}
