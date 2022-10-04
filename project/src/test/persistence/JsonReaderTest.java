package persistence;

import model.ListOfRating;
import model.Rating;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Reference code from JsonSerializationDemo
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfRating lr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRatingList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRatingList.json");
        try {
            ListOfRating lr = reader.read();
            assertEquals(0, lr.numRatings());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRatingList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRatingList.json");
        try {
            ListOfRating lr = reader.read();
            List<Rating> ratings = lr.getRatings();
            assertEquals(2, ratings.size());
            checkRating("Red Blue","CPSC110","4",
                    "She is a perfect professor for this course.",ratings.get(1));
            checkRating("Apple Banana","CPSC210","4",
                    "He is a perfect professor for this course.",ratings.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
