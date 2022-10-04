package persistence;


import model.ListOfRating;
import model.Rating;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads ListOfRating from JSON data stored in file
// Reference code from JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads list of ratings from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfRating read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfRating(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses list of rating from JSON object and returns it
    private ListOfRating parseListOfRating(JSONObject jsonObject) {
        ListOfRating lr = new ListOfRating();
        addRatings(lr, jsonObject);
        return lr;
    }

    // MODIFIES: lr
    // EFFECTS: parses ratings from JSON object and adds them to list of rating
    private void addRatings(ListOfRating lr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ratings");
        for (Object json : jsonArray) {
            JSONObject nextRating = (JSONObject) json;
            addRating(lr, nextRating);
        }
    }

    // MODIFIES: lr
    // EFFECTS: parses rating from JSON object and adds it to list of rating
    private void addRating(ListOfRating lr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String courseCode = jsonObject.getString("courseCode");
        String quality = jsonObject.getString("quality");
        String comment = jsonObject.getString("comment");
        Rating rating = new Rating(name, courseCode,quality,comment);
        lr.addRating(rating);
    }
}
