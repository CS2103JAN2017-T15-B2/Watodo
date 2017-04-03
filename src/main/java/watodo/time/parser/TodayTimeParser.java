package watodo.time.parser;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@@author A0143873Y
/**
 * Parse date and time in the form of "today 5.00pm"
 */
public class TodayTimeParser implements TimeParser {
    private static final String TODAY_TIME_PATTERN = "(?i)^today (1[012]|[1-9]).[0-5][0-9](\\s)?(am|pm)";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d h.mma");
    private Clock clock = Clock.systemDefaultZone();

    /**
     * Checks if the user input corresponds to the format of the respective
     * time parser.
     *
     * @param input
     * @return true if format matches and false otherwise.
     */
    @Override
    public boolean applyTo(String input) {
        return input.matches(TODAY_TIME_PATTERN);
    }

    /**
     * Parses the userInput string to a time instance. This method assumes that {@code respondTo} returns {@code true}.
     * @param input The input to parse.
     * @return The {@code LocalDateTime}, which is the result of parsing {@code input}.
     */
    @Override
    public String parse(String input) {
        assert input.matches(TODAY_TIME_PATTERN);

        String todayDate = LocalDate.now(this.clock).toString();
        String time = input.split("\\s+")[1];
        String todayDateTime = todayDate + " " + time;

        return LocalDateTime.parse(todayDateTime.toUpperCase(), formatter).format(defaultFormatter);
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }
}
