package watodo.model.task;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

//@@author A0119505J
public class StatusTest {

    private static final String NOT_COMPLETED_MESSAGE = "(Task is incomplete)";
    private static final String COMPLETED_MESSAGE = "(Task is completed)";
    private static final String UNDEFINED_STATUS_MESSAGE = "An internal error has occured. Status not readable.";

    @Test
    public void isStatusLevel() {
        // invalid status cases
        Status testCase3 = new Status(2);
        assertTrue(testCase3.toString().equals(UNDEFINED_STATUS_MESSAGE));

        // valid status cases
        Status testCase1 = new Status(0);
        Status testCase2 = new Status(1);
        assertTrue(testCase1.toString().equals(NOT_COMPLETED_MESSAGE));
        assertTrue(testCase2.toString().equals(COMPLETED_MESSAGE));
    }
}
