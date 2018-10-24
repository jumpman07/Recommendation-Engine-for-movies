
/**
 * Write a description of RaterDatabase here. This class contains methods to add and manipulate rater data
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class RaterDatabase {
    
    private static HashMap<String,Rater> ourRaters; // A map mapping rater ID to rater object
    
    private static void initialize(){
        // To be called only from addRatings
        if (ourRaters == null)
            ourRaters = new HashMap<String,Rater>();
    }
    
    public static void initialize(String filename){
        // This method fires the RaterDatabase; call this method in your runner class
        if (ourRaters == null){
            ourRaters = new HashMap<String,Rater>();
            addRatings("data/" + filename);
        }
    }
    
    public static void addRatings(String filename){
        
        initialize();
        FileResource fr = new FileResource(filename);
        
        for (CSVRecord rec: fr.getCSVParser()){
            String id = rec.get("rater_id");
            String item = rec.get("movie_id");
            String rating = rec.get("rating");
            addRaterRating(id,item,Double.parseDouble(rating));
        }
    }
    
    public static void addRaterRating(String raterID, String movieID, double rating){
        
        initialize();
        Rater rater = null;
        
        // Add the rater object to ourRaters if it doesn't exist already
        if (ourRaters.containsKey(raterID))
            rater = ourRaters.get(raterID);
        else{
            rater = new EfficientRater(raterID); // Create a new rater object
            ourRaters.put(raterID,rater);
        }
        rater.addRating(movieID,rating);  // Add the movieId and its rating to the rater object
    }
    
    public static Rater getRater(String id){
        
        // Returns a rater object corresponding to a raterId
        initialize();
        return ourRaters.get(id);
    }
    
    public static ArrayList<Rater> getRaters(){
        
        initialize();
        ArrayList<Rater> list = new ArrayList<Rater>(ourRaters.values()); // Create a new ArrayList of rater objects
        return list;
    }
    
    public static int size(){
        return ourRaters.size();
    }
}
