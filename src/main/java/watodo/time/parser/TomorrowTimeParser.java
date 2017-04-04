package watodo.time.parser;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@@author A0143873Y
/**
 * Parse date and time wit the format of  "tomorrow 5.00pm"
 */
public class TomorrowTimeParser implements TimeParser {
    private static final String TOMORROW_TIME_PATTERN = "(?i)^tmr (1[012]|[1-9]).[0-5][0-9](\\s)?(am|pm)";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d h.mma");
    private Clock clock = Clock.systemDefaultZone();

    /**
     * Verify whether user input applies to the format of the respective
     * time parser.
     *
     * @param input
     * @return true if the format fits and false otherwise.
     */
    @Override
    public boolean applyTo(String input) {
        return input.matches(TOMORROW_TIME_PATTERN);
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
        assert input.matches(TOMORROW_TIME_PATTERN);

        String tomorrowDate = LocalDate.now(this.clock).plusDays(1).toString();
        String time = input.split("\\s+")[1];
        String tomorrowDateTime = tomorrowDate + " " + time;

        return LocalDateTime.parse(tomorrowDateTime.toUpperCase(), formatter).toString();
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }
}
