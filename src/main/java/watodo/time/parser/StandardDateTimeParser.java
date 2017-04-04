/**
 * 
 */
package watodo.time.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

//@@author A0143873Y
/**
 * Parse date and time wit the format of  "16 Oct 2016 5.00pm"
 */
public class StandardDateTimeParser implements TimeParser {
    DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
                                        .appendPattern("d MMM yyyy h.mma").toFormatter();

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
        return LocalDateTime.parse(input, formatter).format(defaultFormatter);
    }

}
