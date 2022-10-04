package persistence;


import model.Rating;

import static org.junit.jupiter.api.Assertions.assertEquals;

// To check the rating provided
// Reference code from JsonSerializationDemo
public class JsonTest {
    protected void checkRating(String name, String courseCode, String quality, String comment, Rating rating) {
        assertEquals(name, rating.getName());
        assertEquals(courseCode, rating.getCourseCode());
        assertEquals(quality, rating.getQuality());
        assertEquals(comment, rating.getComment());
    }
}


