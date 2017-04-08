package watodo.time.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

//@@author A0143873Y

public class TimeParserSelectorTest {

    @Test
    public void applicableTimeFormat() {
        
        StandardDateTimeParser standard = new StandardDateTimeParser();
        ISODateTimeParser iso = new ISODateTimeParser();
        TodayTimeParser today = new TodayTimeParser();
        TomorrowTimeParser tmr = new TomorrowTimeParser();
        
        //valid format
        assertTrue(standard.applyTo("16 Oct 2016 5.00pm"));
        assertTrue(standard.applyTo("30 jan 2017 1.59pm"));
        
        assertTrue(iso.applyTo("2017-09-23 8.00am"));
        assertTrue(iso.applyTo("1996-01-07 1.00am"));
        
        assertTrue(today.applyTo("today 12.01am"));
        assertTrue(today.applyTo("today 11.59pm"));
        
        assertTrue(tmr.applyTo("tmr 10.00am"));
        assertTrue(tmr.applyTo("tmr 4.00pm"));
        
        //invalid formats
        assertFalse(standard.applyTo("16-Oct-2016 5.00pm"));
        assertFalse(standard.applyTo("30 january 2017 1.59pm"));
        assertFalse(standard.applyTo("30 jan 2017 01:59"));
        assertFalse(standard.applyTo("30 jan 2017 00:09"));
        
        assertFalse(iso.applyTo("2017/09/23 8.00am"));
        assertFalse(iso.applyTo("1996-01-07 1:00"));
        
        assertFalse(today.applyTo("today 12:01"));
        assertFalse(today.applyTo("today 13.59pm"));
        
        assertFalse(tmr.applyTo("tmr 1000"));
        assertFalse(tmr.applyTo("tmr 16:00"));
        
    }
    
}
