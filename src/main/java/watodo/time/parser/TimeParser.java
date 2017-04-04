
package watodo.time.parser;

import java.time.format.DateTimeFormatter;

import watodo.model.task.Time;

//@@author A0143873Y
/**
 * All time parser classes must implement this interface
 *
 */
public interface TimeParser {
    
    public static final DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern(Time.PATTERN);
    
    /**
     * Verify whether user input applies to the format of the respective
     * time parser.
     *
     * @param input
     * @return true if the format fits and false otherwise.
     */
    public boolean applyTo(String time);
    
    
    /**
     * Parses the userInput string to a standardized string format that
     * is acceptable to be passed to a Time constructor.
     * This method assumes that {@code applyTo} returns {@code true}.
     * @param input The input for parsing.
     * @return The {@code LocalDateTime}, the converted string {@code input}.
     */
    public String parse(String input);
}
