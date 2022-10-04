package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of student ratings
public class ListOfRating implements Writable {

    private ArrayList<Rating> list;


    //MODIFIES: this
    // constructs a new empty student rating list
    public ListOfRating() {
        list = new ArrayList<>();
    }

    // MODIFIES: this
    // add a rating to the rating list
    public void addRating(Rating rating) {
        list.add(rating);
        EventLog.getInstance().logEvent(new Event("Rating of professor " + rating.getName() + " is added"));
    }

    // MODIFIES: this
    // delete a rating from the rating list
    public void deleteRating(int ratingIndex) {
        if (ratingIndex >= 0 && ratingIndex < list.size()) {
            Rating rating = list.get(ratingIndex);
            list.remove(rating);
            EventLog.getInstance().logEvent(new Event(
                    "Rating of professor " + rating.getName() + " is deleted"));
        } else {
            System.out.println("Invalid Index");
        }
    }

    //EFFECTS:returns an unmodifiable list of ratings
    public ArrayList<Rating> getList() {
        return list;
    }

    //EFFECTS: returns rating by entering its index
    public Rating getRating(int index) {
        return list.get(index);
    }

    //EFFECTS: returns a list of ratings searched by professor's full name
    public ArrayList<Rating> searchByName(String searchName) {
        ArrayList<Rating> listSearchedByName = new ArrayList<>();
        for (Rating rating : list) {
            String fullName = rating.getName();
            if (fullName.equals(searchName)) {
                listSearchedByName.add(rating);
            }
        }
        return listSearchedByName;
    }

    //EFFECTS: returns a list of ratings searched by course code
    public ArrayList<Rating> searchByCourseCode(String searchCourseCode) {
        ArrayList<Rating> listSearchByCourseCode = new ArrayList<>();
        for (Rating rating : list) {
            String courseCode = rating.getCourseCode();
            if (courseCode.equals(searchCourseCode)) {
                listSearchByCourseCode.add(rating);
            }
        }
        return listSearchByCourseCode;
    }

    // EFFECTS: return ratings in the list of ratings as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ratings",ratingToJson());
        return json;
    }

    // EFFECTS: returns ratings in this list of ratings as a JSON array
    private JSONArray ratingToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Rating r : list) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }


    //EFFECTS:returns an unmodifiable list of ratings
    public ArrayList<Rating> getRatings() {
        return list;
    }

    //EFFECTS: returns number of tasks in the Rating list
    public int numRatings() {
        return list.size();
    }




}
