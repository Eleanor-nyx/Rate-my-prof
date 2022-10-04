package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EventTest {
    private Event e;
    private Date d;


    @BeforeEach
    public void runbefore() {
        e = new Event("This is the new Event.");
        d = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("This is the new Event.", e.getDescription());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "This is the new Event.", e.toString());
    }

    @Test
    public void testGetDate() {
        assertEquals(d,e.getDate());
    }

    @Test
    public void testEquals() {
        Event thisEvent = new Event("This is the new Event.");
        assertTrue(thisEvent.equals(e));
        assertFalse(e.equals(null));
    }

}