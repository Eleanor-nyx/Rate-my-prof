package model;

import org.json.JSONObject;
import persistence.Writable;

// Represent a rating having the professor's name, the course the professor is teaching, a quality rating out of 5 and
// a comment about the professor.
public class Rating implements Writable {

    private String name;                      // name of the professor eg. "Steven Jobs"
    private String courseCode;                // the course code the professor is teaching eg. "CPSC210"
    private String quality;                   // a quality rating for the professor out of 5 eg. "4"
    private String comment;                   // a comment about the professor eg. "professor Jobs is very interesting"




    //EFFECTS: creates a new rating
    public Rating(String name, String courseCode, String quality, String comment) {
        this.name = name;
        this.courseCode = courseCode;
        this.quality = quality;
        this.comment = comment;
    }

    //EFFECTS: returns professor name
    public String getName() {
        return name;
    }

    //EFFECTS: returns course code of the professor
    public String getCourseCode() {
        return courseCode;
    }

    //EFFECTS: returns quality rating of the professor
    public String getQuality() {
        return quality;
    }

    //EFFECTS: returns comment of the professor
    public String getComment() {
        return comment;
    }


    //EFFECTS: returns the string of the whole student rating
    @Override
    public String toString() {
        return "Student rating{"
                +
                "Professor name :'" + name + '\''
                +
                ", Course code :'" + courseCode + '\''
                +
                ", Quality rating :'" + quality + '\''
                +
                ", Student comment :'" + comment + '\''
                +
                '}';

    }

    // EFFECTS: return rating as a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("courseCode",courseCode);
        json.put("quality",quality);
        json.put("comment",comment);
        return json;

    }



}
