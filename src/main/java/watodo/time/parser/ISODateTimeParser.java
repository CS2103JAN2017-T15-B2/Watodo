package watodo.time.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//@@author A0143873Y
/**
 * Parse date and time wit the format of  "2016-10-01 2:00pm"
 */
public class ISODateTimeParser implements TimeParser {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d h.mma");

    /**
     * Verify whether user input applies to the format of the respective
     * time parser.
     *
     * @param input
     * @return true if the format fits and false otherwise.
     */
    @Override
    public boolean applyTo(String input) {
        try {
            parse(input);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Parses the userInput string to a standardized string format that
     * is acceptable to be passed to a Time constructor.
     * This method assumes that {@code applyTo} returns {@code true}.
     * @param input The input for parsing.
     * @return The {@code LocalDateTime}, the converted string {@code input}.
     */
    @Override
    public String parse(String input) {
        return LocalDateTime.parse(input.toUpperCase(), formatter).toString();
    }
}
