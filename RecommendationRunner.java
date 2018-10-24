
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.*;

public class RecommendationRunner implements Recommender{
    
    public ArrayList<String> getItemsToRate(){
        
        /*
         * Return an array list of 10-20 movies for a rater to rate that were released after 2000
         */
        
        // Fire the RaterDatabase class and print the number of raters in the file
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
            
        // Fire the moviedatabase and Print number of movies in database
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are: " + MovieDatabase.size());
        
        FourthRatings fourthRat = new FourthRatings(); // Create an object of class FourthRatings
        int minRaters = 1;                             // Minimum raters for a movie rating to be valid
        
        Filter f = new YearAfterFilter(2000);  // YearAfter filter which will select movies after 2000
        ArrayList<Rating> movieList = fourthRat.getAverageRatingsByFilter(minRaters,f); // List of Ratings of movies after 2000
        
        // Select 10-20 movies
        ArrayList<String> ItemsToRate = new ArrayList<String>();
        for (int i = 0; i < Math.min(10,movieList.size()); i++){
            Rating r = movieList.get(i);
            ItemsToRate.add(r.getItem());
        }    
        
        return ItemsToRate;
    }
    
    public void printRecommendationsFor (String webRaterID){
        
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        
        FourthRatings fourthRat = new FourthRatings(); // Create an object of class FourthRatings
        ArrayList<Rating> similarMovies = fourthRat.getSimilarRatings(webRaterID,numSimilarRaters,minimalRaters);
        
        if (similarMovies!=null){
            
            System.out.println("Recommended movies: ");
            for (int i = 0; i < Math.min(10,similarMovies.size()); i++){
                Rating r = similarMovies.get(i);
                System.out.println(MovieDatabase.getTitle(r.getItem()));  // Add the title of the movie
            }
        }
        else
            System.out.println("No movies recommended for your taste");
    } 
}
