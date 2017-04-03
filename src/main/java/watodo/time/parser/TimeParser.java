
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
     * Checks if the user input applies to the format of the respective time parser.
     *
     * @param time (inlcuding date and time) as a string
     * @return true if format matches and false otherwise.
     */
    public boolean applyTo(String time);
    
    
    /**
     * Parses the userInput string to a LocalDateTime time instance. This method assumes that {@code respondTo} returns {@code true}.
     * @param input The input to parse.
     * @return The {@code LocalDateTime}, which is the result of parsing {@code input}.
     */
    public String parse(String input);
}
