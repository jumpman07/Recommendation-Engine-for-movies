
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings(){
        
        // Prints general info on the movies and ratings
        
        SecondRatings secondRat = new SecondRatings("ratedmoviesfull.csv","ratings.csv"); // Create a SecondRatings object
        System.out.println("Number of movies: " + secondRat.getMovieSize());
        System.out.println("Number of raters: " + secondRat.getRaterSize());
        
        // Print ratings in order
        int minRaters = 12;
        ArrayList<Rating> ratingsList = secondRat.getAverageRatings(minRaters);
        Collections.sort(ratingsList);  // Sort the ratingsList
        
        int count = 0;
        if (ratingsList != null){
            for (int i = 0; i < ratingsList.size(); i++){
                double rating = ratingsList.get(i).getValue();
                String id = ratingsList.get(i).getItem();
                String title = secondRat.getTitle(id);
                if (!title.equals("not found") && rating > 0.0){
                    System.out.println(rating + "  " + title);
                    count++;
                }
            }
        }
        
        System.out.println("More than 50 raters: " + count);
    }
    
    public void getAverageRatingOneMovie(){
        
        // Prints the average ratings of a movie specified by its title
        SecondRatings secondRat = new SecondRatings("ratedmoviesfull.csv","ratings.csv"); // Create a SecondRatings object
        
        int minRaters = 3;
        ArrayList<Rating> ratingsList = secondRat.getAverageRatings(minRaters);
        
        String title = "Vacation";
        String id    = secondRat.getID(title);
        if (!id.equals("NO SUCH TITLE")){
            for (int i = 0; i < ratingsList.size(); i++){
                if (ratingsList.get(i).getItem().equals(id)){
                    System.out.println(ratingsList.get(i).getValue());
                    return;
                }
            }
        }
        System.out.println(title + " does not exist");
    }
}
