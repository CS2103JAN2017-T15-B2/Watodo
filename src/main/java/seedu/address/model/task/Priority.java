/* @@author A0119505J */
package seedu.address.model.task;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Task's name in the task manager. Guarantees: immutable; is
 * valid as declared in {@link #isValidName(String)}
 */
public class Priority {
    public static final String MESSAGE_PRIORITY_CONSTRAINTS = "Priority should be specified as 'high', 'med' or 'low'.";
    public static final String PRIORITY_VALIDATION_REGEX = "\\b(high)|(low)|(med)\\b";

    public final String priorityLevel;

    public Priority(String priority) throws IllegalValueException {
        if (priority != null && priority != "") {
            priority = priority.trim().toLowerCase();
            if (!isPriorityLevel(priority)) {
                throw new IllegalValueException(MESSAGE_PRIORITY_CONSTRAINTS);
            }
            this.priorityLevel = priority;
        } else {
            this.priorityLevel = "low";
        }
    }

    public static boolean isPriorityLevel(String test) {
        return test.matches(PRIORITY_VALIDATION_REGEX);
    }

    public String toString() {
        return priorityLevel;
    }

    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                        && this.priorityLevel.equals(((Priority) other).priorityLevel)); // state
                                                                                         // check
    }

    @Override
    public int hashCode() {
        return priorityLevel.hashCode();
    }

}
