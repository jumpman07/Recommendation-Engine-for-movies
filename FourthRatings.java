
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.*;

public class FourthRatings {
    
    private double getAverageByID(String id, int minimalRaters){
        
        // Returns the average rating of a movie given by its ID if there are a minimum number of raters who provided a rating to the movie
        
        int numRaters = 0; // A count to store number of raters who provided rating to the movie given by id
        double totalRating = 0.0;
        
        /* Iterate through myRaters to check which raters rated the movie using the .hasRating(id) method. If a rating exists,
         * increment the counter numRaters and then get the rating and add it to the running sum totalRating
         */ 
        for (int i = 0; i < RaterDatabase.size(); i++){
            Rater curRater = RaterDatabase.getRaters().get(i); // Use .getRaters() method of the RaterDatabase class to return all the rater objects
            if (curRater.hasRating(id)){
                numRaters++;
                totalRating += curRater.getRating(id);
            }
        }
        
        // Return average if numRaters exceeds minimalRaters
        if (numRaters >= minimalRaters)
            return totalRating/numRaters;
        else
            return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        
        // Returns a list of Rating objects (movie,rating) that were rated by atleast a minimum number of raters (minimalRaters).
        
        ArrayList<Rating> list = new ArrayList<Rating>(); // Create an empty list to store Rating objects that has movies and their ratings, rated by a minimum # of raters (minimalRaters)
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        /*
         * Iterate through the myMovies list. Supply each movieId to getAverageByID method to get their average ratings.
         * Then create a Rating object with this new rating and the current movieID and add it to the list.
         */
        
        for (int i = 0; i < myMovies.size(); i++){
            String movieId = myMovies.get(i);
            double avgRating = getAverageByID(movieId, minimalRaters); // Use .getAverageByID() method to get the average rating
            Rating newRating = new Rating(movieId,avgRating);    // Create a new Rating object with movieId and its average rating.
            list.add(newRating);
        }
        
        return list;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        
        // Returns a list of Rating objects (movieId and its rating) which were rated by at least a minimal number of raters and satisfy the filterCriteria
        
        ArrayList<Rating> ratingList = new ArrayList<Rating>(); // Empty list to store the Rating objects who qualify.
        ArrayList<String> movieList = MovieDatabase.filterBy(filterCriteria);  // Get a list of movie IDs that satisfy the filterCriteria
        
        /*
         * Iterate through the myMovies list. Supply each movieId to getAverageByID method to get their average ratings.
         * Then create a Rating object with this new rating and the current movieID and add it to the list.
         */
        
        for (int i = 0; i < movieList.size(); i++){
            String movieId = movieList.get(i);
            double avgRating = getAverageByID(movieId, minimalRaters); // Use .getAverageByID() method to get the average rating
            
            if (avgRating > 0.0){   // Add only if it was rated by minimalRaters
                Rating newRating = new Rating(movieId,avgRating);    // Create a new Rating object with movieId and its average rating.
                ratingList.add(newRating);
            }
        }
        
        return ratingList;
    }
    
    private double dotProduct(Rater me, Rater r){
        /*
         * This method should first translate a rating from the scale 0 to 10 to the scale -5 to 5 
         * and return the dot product of the ratings of movies that they both rated. 
         * This method will be called by getSimilarities.
         */
        
        ArrayList<String> myRatedMovies = me.getItemsRated(); // Obtain a list of movies (movieId) rated by rater me
        ArrayList<String> otherRatedMovies = r.getItemsRated(); // Obtain a list of movies (movieId) rated by rater r
        
        double result = 0.0;
        
        /*
         * If a movie is rated by both the raters r and me; the add their product to the result.
         * Also remember to subtract 5 from the rating
         */
        for (String movieId : myRatedMovies){
            
            if (otherRatedMovies.contains(movieId)){
                double myRating = me.getRating(movieId) - 5;
                double otherRating = r.getRating(movieId) - 5;
                result += myRating*otherRating;
            }
        }
        
        return result;
    }
    
    private ArrayList<Rating> getSimilarities(String myId){
        /*
         * Returns a list of Rating objects sorted in reverse order (from highest to lowest) and including only those ratings that have a positive similarity with the ratings done by rater myId.
         * Use the .dotProduct() method on rater given by the raterId in a Rating object and the rater given by myId
         */
        
        ArrayList<Rating> list = new ArrayList<Rating>(); // Empty list of similar rating objects.
        Rater me  = RaterDatabase.getRater(myId);         // Get the rater object corresponding to myId
        
        ArrayList<Rater> allRaters = RaterDatabase.getRaters(); // A list of all the rater objects in the RaterDatabase.
        
        for (Rater r : allRaters){
            String curRaterId = r.getID(); // Use .getID() method of the EfficientRater class to retrieve the current rater's ID
            
            if (!curRaterId.equals(myId)){
                double curDotProduct = dotProduct(me,r); // Compute similarity using the .dotProduct method
                
                // If the similarity is positive, add the current rater with its rating to the list
                if (curDotProduct > 0.0)
                    list.add(new Rating(curRaterId,curDotProduct));
            }
        }
        
        // Sort the list in reverse order
        Collections.sort(list,Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        
        /*
         * Returns an an ArrayList of type Rating, of movies and their weighted average ratings using only the top numSimilarRaters with 
         * positive ratings and including only those movies that have at least minimalRaters ratings from those most similar raters. 
         */
        return getSimilarRatingsByFilter(id,numSimilarRaters,minimalRaters, new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        
        /*
         * Returns an ArrayList of Rating objects which have similar ratings to the user along with a filter.
         */
        
         ArrayList<Rating> similarRatings = getSimilarities(id); // List of similar raters and their ratings to the rater given by raterId id
        ArrayList<Rating> recMovies      = new ArrayList<Rating>();
        
        /*
         * For each movie filtered by the filter criteria, and for each of numSimilarRaters calculate the weighted average and add it to the recMovies list
         */
        for (String movieId : MovieDatabase.filterBy(filterCriteria)){
            int numRatings = 0; // Counter to count #of raters
            double total = 0.0;
            
            for (int i = 0; i < numSimilarRaters; i++){
                Rating curRating = similarRatings.get(i); // Current rating
                double weightOfRating = curRating.getValue(); // Get the closeness measure; which is also the weight
                String raterId = curRating.getItem();         // Get the current raterId
                Rater rater = RaterDatabase.getRater(raterId); // Get the rater object for raterId
                
                if (rater.hasRating(movieId)){
                    numRatings++;
                    double movieRating = rater.getRating(movieId);
                    total += weightOfRating*movieRating;
                }
            }
            
            if (numRatings >= minimalRaters)
                recMovies.add(new Rating(movieId,total/numRatings));
        }
            
        Collections.sort(recMovies,Collections.reverseOrder());
        return recMovies;
    }
    
    
}
