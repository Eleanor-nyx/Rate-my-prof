package persistence;



import model.ListOfRating;
import model.Rating;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonTest;
import persistence.JsonWriter;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// Reference code from JsonSerializationiDemo
class JsonWriterTest extends JsonTest {


    @Test
    void testWriterInvalidFile() {
        try {
            ListOfRating lr = new ListOfRating();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ListOfRating lr = new ListOfRating();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyRatingList.json");
            writer.open();
            writer.write(lr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyRatingList.json");
            lr = reader.read();
            assertEquals(0, lr.numRatings());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ListOfRating lr = new ListOfRating();
            lr.addRating(new Rating("Red Blue","CPSC110","4",
                    "She is a perfect professor for this course"));
            lr.addRating(new Rating("Apple Banana","CPSC210","4",
                    "He is a perfect professor for this course"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralRatingList.json");
            writer.open();
            writer.write(lr);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralRatingList.json");
            lr = reader.read();
            List<Rating> ratings = lr.getRatings();
            assertEquals(2, ratings.size());
            checkRating("Red Blue","CPSC110","4",
                    "She is a perfect professor for this course",ratings.get(0));
            checkRating("Apple Banana","CPSC210","4",
                    "He is a perfect professor for this course",ratings.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}