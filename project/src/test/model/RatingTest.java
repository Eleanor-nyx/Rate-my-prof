package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatingTest {

    private Rating testRating1;
    private Rating testRating2;
    private Rating testRating3;



    @BeforeEach
    void runBefore() {
        testRating1 = new Rating("Steve Jobs","CPSC210","5","A fabulous professor.");
        testRating2 = new Rating("Natural Recursion","CPSC110","4",
                "He was very patient with Dr.Racket questions.");
        testRating3 = new Rating("Logic Logisim","CPSC121","4",
                "MagicBox chips sometimes drive me crazy, but Professor Logisim can really help.");

    }

    @Test
    void testGetName() {
        assertEquals("Steve Jobs",testRating1.getName());
        assertEquals("Natural Recursion",testRating2.getName());
        assertEquals("Logic Logisim",testRating3.getName());

    }

    @Test
    void testGetCourseCode() {
        assertEquals("CPSC210",testRating1.getCourseCode());
        assertEquals("CPSC110",testRating2.getCourseCode());
        assertEquals("CPSC121",testRating3.getCourseCode());
    }

    @Test
    void testGetQuality() {
        assertEquals("5",testRating1.getQuality());
        assertEquals("4",testRating2.getQuality());
        assertEquals("4",testRating3.getQuality());

    }

    @Test
    void testGetComment() {
        assertEquals("A fabulous professor.",testRating1.getComment());
        assertEquals("He was very patient with Dr.Racket questions.",testRating2.getComment());
        assertEquals("MagicBox chips sometimes drive me crazy, but Professor Logisim can really help.",
                testRating3.getComment());

    }

    @Test
    void testToString() {
        assertEquals("Student rating{Professor name :'Steve Jobs', Course code :'CPSC210', " +
                "Quality rating :'5', Student comment :'A fabulous professor.'}",testRating1.toString());
    }

}