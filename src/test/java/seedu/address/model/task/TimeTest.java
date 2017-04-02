package seedu.address.model.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;

//@@author A0143873Y
public class TimeTest {

    @Test
    public void isValidTime() {
        // invalid phone numbers
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        //boundary values
        assertFalse(Time.isValidTime("10/13/1996 05:00")); // invalid month
        assertFalse(Time.isValidTime("32/08/1996 05:00")); // invalid day
        assertFalse(Time.isValidTime("10/00/1996 05:00")); // invalid month #2
        assertFalse(Time.isValidTime("01/08/1996 24:00")); // invalid hour
        assertFalse(Time.isValidTime("30/01/1996 23:60")); //invalid minute

        // valid phone numbers
        assertTrue(Time.isValidTime("06/01/2016 00:00"));
        assertTrue(Time.isValidTime("10/08/1996 05:00"));
        assertTrue(Time.isValidTime("01/12/2015 23:59"));
    }

    @Test
    public void timeConstructor() {

        try {
            Time time1 = new Time("06/01/2026 03:00");
            assertEquals("Two Strings not equal", time1.toString(), "06/01/2026 03:00");

            Time time2 = new Time("06/03/2016 15:00");
            String output2 = time2.getStoredValue();
            assertEquals("Two Strings not equal", output2, "2016-03-06T15:00");

        } catch (IllegalValueException e) {
            fail("Failure in constructing time class");
        }
    }
}
