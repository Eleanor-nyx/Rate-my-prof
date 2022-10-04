package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ListOfRatingTest {

    ListOfRating testList = new ListOfRating();
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
    void testList() {
        assertEquals(0,testList.getList().size());
    }

    @Test
    void testAddRating() {
        testList.addRating(testRating1);
        testList.addRating(testRating2);
        assertEquals(2,testList.getList().size());
        assertTrue(testList.getList().contains(testRating2));
        assertFalse(testList.getList().contains(testRating3));
    }

    @Test
    void testDeleteRating() {
        testList.addRating(testRating2);
        testList.addRating(testRating1);
        assertEquals(2,testList.getList().size());
        assertTrue(testList.getList().contains(testRating1));
        testList.deleteRating(1); //valid index
        assertFalse(testList.getList().contains(testRating1));
        testList.deleteRating(5); //invalid - >list.size
        assertTrue(testList.getList().contains(testRating2));
        testList.deleteRating(-2); //invalid - <0
        assertTrue(testList.getList().contains(testRating2));



    }

    @Test
    void testGetRating() {
        testList.addRating(testRating2);
        testList.addRating(testRating1);
        testList.addRating(testRating3);
        assertEquals(3,testList.getList().size());
        assertEquals(testRating2,testList.getRating(0));
        assertEquals(testRating1,testList.getRating(1));
        assertEquals(testRating3,testList.getRating(2));


    }

    @Test
    void testSearchByName() {
        testList.addRating(testRating1);
        testList.addRating(testRating3);
        assertTrue(testList.searchByName("Steve Jobs").contains(testRating1));
        assertFalse(testList.searchByName("Natural Recursion").contains(testRating2));

    }

    @Test
    void testSearchByCourseCode() {
        testList.addRating(testRating1);
        testList.addRating(testRating3);
        assertTrue(testList.searchByCourseCode("CPSC210").contains(testRating1));
        assertFalse(testList.searchByCourseCode("CPSC110").contains(testRating2));

    }


}