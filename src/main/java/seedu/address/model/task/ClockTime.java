package seedu.address.model.task;

import seedu.address.commons.exceptions.IllegalValueException;

//@@author A0143873Y
/**
 * Represents a Task's time in the Tasklist.
 * Guarantees: immutable; is valid as declared in {@link #isValidClockTime(String)}
 */

public class ClockTime {
    //private final Logger logger = LogsCenter.getLogger(ClockTime.class);

    public static final String MESSAGE_START_TIME_CONSTRAINTS =
            "Clock time of task should be 24-hour form, eg: 23:59";
    public static final String START_TIME_VALIDATION_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

    public final String value;

    /**
     * Validates given startTime.
     *
     * @throws IllegalValueException if given startTime address string is invalid.
     */
    public ClockTime(String startTime) throws IllegalValueException {
        assert (startTime != null);
        String trimmedClockTime = startTime.trim();
        if (!isValidClockTime(trimmedClockTime)) {
            throw new IllegalValueException(MESSAGE_START_TIME_CONSTRAINTS);
        }
        this.value = trimmedClockTime;
    }

    /**
     * Returns if a given string is a valid task startTime.
     */
    public static boolean isValidClockTime(String test) {
        return test.matches(START_TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClockTime // instanceof handles nulls
                && this.value.equals(((ClockTime) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
