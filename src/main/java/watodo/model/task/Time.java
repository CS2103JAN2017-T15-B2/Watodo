package watodo.model.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import watodo.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's Time value in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */

//@@author A0143873Y
public class Time {

    public static final String PATTERN = "dd/MM/yyyy HH:mm";
    public static final String MESSAGE_TIME_CONSTRAINTS = "Task time should follow '" + PATTERN + "' format.";
    public static final String TIME_VALIDATION_REGEX =
            "^([1-9]|([012][0-9])|(3[01]))/([0]{0,1}[1-9]|1[012])/\\d\\d\\d\\d ([01]\\d|2[0-3]):?([0-5]\\d)$";

    public final LocalDateTime dateTime;
    public final String value;

    private static DateTimeFormatter format = new DateTimeFormatterBuilder().parseCaseInsensitive()
            .appendPattern(PATTERN).toFormatter();

    /**
     * Validates given Time string.
     *
     * @throws IllegalValueException if given Time string is invalid.
     */
    public Time(String time) throws IllegalValueException {
        if (time != null) {
            String trimmedTime = time.trim();
            if (!isValidTime(trimmedTime)) {
                throw new IllegalValueException(MESSAGE_TIME_CONSTRAINTS);
            }
            this.value = trimmedTime;
            this.dateTime = LocalDateTime.parse(time, format);
        } else {
            this.value = null;
            this.dateTime = null;
        }
    }

    /**
     * Returns true if a given string is a valid task Time string.
     */
    public static boolean isValidTime(String test) {
        return test.matches(TIME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    public String getStoredValue() {
        return dateTime.toString();
    }

    @Override
    /**
     * compare Time, accept null values.
     * @return 
     * true, if two are the same.
     */
    public boolean equals(Object other) {
        String time = ((Time) other).value;
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && this.value == null ? time == null : this.value.equals(((Time) other).value)); 
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
