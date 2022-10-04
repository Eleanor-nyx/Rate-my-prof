package ui;

import model.ListOfRating;
import model.Rating;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// The Rate My Professor Application
// Reference code from TellerApp
public class RateMyProfApp {

    ListOfRating lor = new ListOfRating();
    private Scanner input = new Scanner(System.in);
    private JsonWriter jsonWriter;
    private JsonReader jsonReader = new JsonReader("./data/list.json");
    private static final String JSON_STORE = "./data/list.json";


    // EFFECTS: runs the Rate My Professor application
    public RateMyProfApp()  throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runRate();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runRate() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAdd();
        } else if (command.equals("d")) {
            doDelete();
        } else if (command.equals("v")) {
            viewRatings();
        } else if (command.equals("e")) {
            searchRating();
        } else if (command.equals("s")) {
            saveListOfRating();
        } else if (command.equals("l")) {
            loadToDoList();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add a rating");
        System.out.println("\td -> delete a rating");
        System.out.println("\tv -> view ratings");
        System.out.println("\te -> search rating");
        System.out.println("\ts -> save the list of ratings to file");
        System.out.println("\tl -> load the list of ratings from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: let user add a rating to the student rating list
    private void doAdd() {

        System.out.print("Enter the full name of the professor, eg. 'Meghan Allen'");
        String name = input.next();

        System.out.print("Enter the course code, eg. 'CPSC210'");
        String courseCode = input.next();

        System.out.print("Enter the quality rating you want to give this professor (out of 5), eg. '4' ");
        String quality = input.next();

        System.out.print("Enter some comment you want to give this professor, eg 'He is very patient.' ");
        String comment = input.next();

        Rating rating = new Rating(name, courseCode, quality, comment);
        lor.addRating(rating);
        System.out.print("Student rating for professor " + name + " is added");
    }

    // MODIFIES: this
    // EFFECTS: let user delete a rating to the student rating list
    private void doDelete() {

        System.out.println("Which rating you would like to delete?");
        int ratingIndex = input.nextInt();
        input.nextLine();
        lor.deleteRating(ratingIndex);
        System.out.println("Your rating has been deleted.");


    }

    // MODIFIES: this
    // EFFECTS: prints out the list of student ratings
    private void viewRatings() {
        List<Rating> ratings = lor.getList();

        for (Rating r : ratings) {
            System.out.println(r);
        }
    }

    // EFFECTS: prints out the search result of the user input
    private void searchRating() {
        System.out.println("\tI want to search by:");
        ArrayList<Rating> searched = (ArrayList<Rating>) searchBy();
        System.out.println(searched);

    }

    // REQUIRES: there are at least one rating in the list of student ratings
    // EFFECTS: let user search by professor name or course code as they wish
    private Object searchBy() {
        String selection = "";
        while (!(selection.equals("n") || selection.equals("c"))) {
            System.out.println("n for search by professor full name");
            System.out.println("c for search by course code");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        if (selection.equals("n")) {
            System.out.println("The full name of the professor is:");
            String profName = input.next();
            if (lor.searchByName(profName).isEmpty()) {
                System.out.println("Not found...");
            }
            return lor.searchByName(profName);
        } else {
            System.out.println("The course code is: ");
            String courseCode = input.next();
            if (lor.searchByCourseCode(courseCode).isEmpty()) {
                System.out.println("Not found...");
            }
            return lor.searchByCourseCode(courseCode);
        }
    }

    // EFFECTS: save the list of ratings to file
    private void saveListOfRating() {
        try {
            jsonWriter.open();
            jsonWriter.write(lor);
            jsonWriter.close();
            System.out.println("Saved the List of ratings to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file" + JSON_STORE);
        }


    }

    // MODIFIES: this
    // EFFECTS: loads list of ratings from file
    private void loadToDoList() {
        try {
            lor = jsonReader.read();
            System.out.println("Loaded the List Of Ratings from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
