
/**
 * Write a description of EfficientRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.lang.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String,Rating> myRatings; // Stores a Rating object corresponding to a movieId.

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        if (!myRatings.containsKey(item))
            myRatings.put(item,new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if (myRatings.containsKey(item))
            return true;
        
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        
        if (myRatings.containsKey(item))
            return myRatings.get(item).getValue(); // Return the rating of the movie given by item
            
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        
        for (String id : myRatings.keySet())
            list.add(id);
        
        return list;
    }
}
