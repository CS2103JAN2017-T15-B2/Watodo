package seedu.address.model.task;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

//@@author A0119505J
public class StatusTest {

    private static final String NOT_COMPLETED_MESSAGE = "Task is incomplete.";
    private static final String COMPLETED_MESSAGE = "Task is completed.";

    @Test
    public void isStatusLevel() {
        // invalid status cases
        Status testCase1 = new Status(0);

        // valid status cases
        Status testCase2 = new Status(1);
        assertTrue(testCase1.toString().equals(NOT_COMPLETED_MESSAGE));
        assertTrue(testCase2.toString().equals(COMPLETED_MESSAGE));
    }
}
