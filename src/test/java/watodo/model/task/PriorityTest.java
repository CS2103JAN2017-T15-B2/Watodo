package watodo.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import watodo.commons.exceptions.IllegalValueException;

//@@author A0119505J
public class PriorityTest {

    @Test
    public void isPriorityLevel() {
        // invalid priority cases
        assertFalse(Priority.isPriorityLevel("")); // empty string
        assertFalse(Priority.isPriorityLevel(" ")); // spaces only
        assertFalse(Priority.isPriorityLevel("priority")); // not expected a string
        assertFalse(Priority.isPriorityLevel("9011p041")); // alphabets within digits

        // valid priority cases
        assertTrue(Priority.isPriorityLevel("high"));
        assertTrue(Priority.isPriorityLevel("med"));
        assertTrue(Priority.isPriorityLevel("low"));
    }

    @Test
    public void isPriorityEqual() throws IllegalValueException {
        Priority priority1 = new Priority("");
        Priority priority2 = new Priority("low");
        assertTrue(priority1.priorityLevel == priority2.priorityLevel);
    }
}
