package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EventLogTest {
    private Event e1;
    private Event e2;
    private Event e3;

    @BeforeEach
    public void runBefore() {
        e1 = new Event("E1");
        e2 = new Event("E2");
        e3 = new Event("E3");
        EventLog el = EventLog.getInstance();
        el.logEvent(e1);
        el.logEvent(e2);
        el.logEvent(e3);


    }

    @Test
    public void testLogEvent() {
        List<Event> l = new ArrayList<Event>();
        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }
        assertTrue(l.contains(e1));
        assertTrue(l.contains(e2));
        assertTrue(l.contains(e3));
    }


    @Test
    public void testClear() {
        EventLog el = EventLog.getInstance();
        el.clear();
        Iterator<Event> it = el.iterator();
        assertTrue(it.hasNext());
        assertEquals("Event log cleared.", it.next().getDescription());
        assertFalse(it.hasNext());
    }
}
