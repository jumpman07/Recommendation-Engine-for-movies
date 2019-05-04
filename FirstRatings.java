
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.lang.*;

public class FirstRatings {
    
    /*
    private ArrayList<Movie> list;  // A list holding Movie objects
    private ArrayList<Rater> raterList; // A list holding rater objects
    */
    private HashMap<String,Integer> numMoviesByDirector; // A map to keep count of number of movies (value) directed by a director(key)
    private HashMap<String,Integer> numRatingsByRater;    // A map to keep count of number of ratings (value) given by a rater(key)
    
    public FirstRatings(){
        //list = new ArrayList<Movie>();
        //raterList = new ArrayList<Rater>();
        numMoviesByDirector = new HashMap<String,Integer>();
        numRatingsByRater   = new HashMap<String,Integer>();
    }
    
    public ArrayList<Movie> loadMovies(String filename){
    /*
     * From each record in the provided filename, extract information about each movie and create an object of class Movie. Add this object to the arrayList of such movie objects
     */   
        ArrayList<Movie> list = new ArrayList<Movie> ();
        FileResource fr = new FileResource(filename);
        
        for (CSVRecord rec: fr.getCSVParser()){
            Movie curMovie = new Movie(rec.get("id"),rec.get("title"),rec.get("year"),rec.get("genre"), rec.get("director"),
            rec.get("country"), rec.get("poster"), Integer.parseInt(rec.get("minutes")));
            
            //Add curMovie to list
            list.add(curMovie);
        }
        
        return list;
    }
    
    private ArrayList<String> numMoviesGenre(String genre, ArrayList<Movie> list){
        
        /*
         * Computes the number of movies that are of the mentioned genre
         */
        
        ArrayList<String> movieList = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++){
            
            Movie curMovie = list.get(i);
            String curGenre = curMovie.getGenres();
            
            if (curGenre.contains(genre) || curGenre.contains(genre.toLowerCase()))
                movieList.add(curMovie.getTitle());
        }
        
        return movieList;
    }
    
    private ArrayList<String> numMoviesWithMins(int numMin, ArrayList<Movie> list){
        /*
         * Computes the number of movies that are greater than given length
         */
        ArrayList<String> movieList = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++){
            
            Movie curMovie = list.get(i);
            if (curMovie.getMinutes() > 150)
                movieList.add(curMovie.getTitle());
        }
        
        return movieList;
    }
    
    private void numDirectors(ArrayList<Movie> list){
        
        // Builds the numMoviesByDirector map from the list of Movie objects
        
        for (int i = 0; i < list.size(); i++){
            Movie curMovie = list.get(i);
            String[] dir = curMovie.getDirector().split(","); // Array of directors as a movie can have multiple directors.
            
            for (int  j = 0; j < dir.length; j++){
                if (numMoviesByDirector.containsKey(dir[j])){
                    int val = numMoviesByDirector.get(dir[j]);
                    numMoviesByDirector.put(dir[j],val+=1);
                }
                else
                    numMoviesByDirector.put(dir[j],1);
            }    
        }
    }
    
    private void moviesWithDirectors(){
        
        int numMovies = 0;
        String director = "";
        
        for (String dir : numMoviesByDirector.keySet()){
            
            if (numMoviesByDirector.get(dir) > numMovies){
                numMovies = numMoviesByDirector.get(dir);
                director = dir;
            }
        }
        
        System.out.println(director + " has a maximum of " + numMovies);
    }
    
    public void testLoadMovies(){
        
        String filename = "ratedmoviesfull.csv";
        ArrayList<Movie> list = loadMovies(filename);
        
        if (list == null){
            System.out.println("List is empty.");
            return;
        }    
        
        /*
        // Print number of movies
        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i).toString());
        */
        System.out.println("Number of movies are: " + list.size());
        
        // count number of movies of given genre
        String genre = "Comedy";
        System.out.println("There are " + numMoviesGenre(genre,list).size() + " movies of " + genre + " genre");
        
        // Count number of movies with t mins
        int t = 150;
        System.out.println("There are " + numMoviesWithMins(t,list).size() + " movies of with length greater than " + t + " minutes.");
        
        // Directors with maxMovies
        numDirectors(list); // Build a map of director and his/her movies
        moviesWithDirectors();
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        
        ArrayList<Rater> raterList = new ArrayList<Rater>();
        FileResource fr = new FileResource("data/" + filename);
        
        for (CSVRecord rec: fr.getCSVParser()){
            String id = rec.get("rater_id");
            Rater curRater = new EfficientRater(id);    // Create a new rater
            double curRating = Double.parseDouble(rec.get("rating"));
            curRater.addRating(rec.get("movie_id"),curRating);  // Add rating to the movie
            raterList.add(curRater);                            // Add the current rater to the list
        }
        
        return raterList;
    }
    
    private int findNumOfRatings (String id, ArrayList<Rater> raterList){
        
        // Returns the number of rating provided by the rater given by id
        int count = 0;
        
        for (int i = 0; i < raterList.size(); i++){
            if (id.equals(raterList.get(i).getID()))  // IDs match
                count++;
        }
        
        return count;
    }
    
    private void buildNumRatingsByRaters(ArrayList<Rater> raterList){
        
        // Builds the numRatingsByRater map.
        for (int i = 0; i < raterList.size(); i++){
            String curId = raterList.get(i).getID();
            if (numRatingsByRater.containsKey(curId)){    // Increment the number of ratings the rater with id curId has provided
                int numRatings = numRatingsByRater.get(curId);
                numRatingsByRater.put(curId,numRatings+=1);
            }
            else
                numRatingsByRater.put(curId,1); // Note the rating provided by the new rater
        }
    }
    
    private void findMaxRatingsAndRaters(){
        
        // Prints the maximum number of ratings a rater has given and prints the rater id and the number of ratings
        String maxRaterId = "";
        int maxNumRatings = 0;
        
        for (String curId : numRatingsByRater.keySet()){
            if (numRatingsByRater.get(curId) > maxNumRatings){
                maxNumRatings = numRatingsByRater.get(curId);
                maxRaterId = curId;
            }
        }
        
        System.out.println("Rater " + maxRaterId + " has a maximum of " + maxNumRatings);
    }
    
    private int findNumOfRatingsOfMovie(String MovieId, ArrayList<Rater> raterList){
        
        // Returns the number of ratings received by the movie denoted by MovieId
        int numRatings = 0;
        
        for (int i = 0; i < raterList.size(); i++){
            if (raterList.get(i).hasRating(MovieId)) // .hasRating(String item) checks if the item was rated by the rater.
                numRatings++;
        }
        
        return numRatings;
    }
    
    private ArrayList<String> ratedMovies(ArrayList<Rater> raterList){
        
        // Returns a list of unique movies rated by the raters in a file
        ArrayList<String> moviesRated = new ArrayList<String>();
        
        for (int i = 0; i < raterList.size(); i++){
            Rater curRater = raterList.get(i);
            ArrayList<String> curMoviesList = curRater.getItemsRated();
            
            for (int j = 0; j < curMoviesList.size(); j++){
                if (!moviesRated.contains(curMoviesList.get(j)))
                    moviesRated.add(curMoviesList.get(j));
            }
        }
        
        return moviesRated;
    }
    
    public void testLoadRaters(){
        
        String filename = "ratings.csv";
        ArrayList<Rater> raterList = loadRaters(filename);                 // Create a list of raters, called raterList
        
        
        // Test number of ratings from a rater.
        
        String raterId = "193";
        System.out.println("Rater id:" + raterId + " has" + findNumOfRatings(raterId,raterList) + " ratings");
        
       
        buildNumRatingsByRaters(raterList); // Build the rater to the number of ratings map
        findMaxRatingsAndRaters(); // Print the rater who has maximum number of ratings along with the number of ratings
        
        // Test what movie had how many ratings
        String movieId = "1798709";
        System.out.println("Movie with movie_id " + movieId + "was rated " + findNumOfRatingsOfMovie(movieId,raterList) + " times");
        
        // Test number of unique movies rated by the raters
        System.out.println("Total of " + ratedMovies(raterList).size() + " unique movies were rated");
        /*
        // Print raterID, number of ratings, and movie and its rating
        for (int i = 0; i < raterList.size(); i++){
            
            System.out.println(raterList.get(i).getID() + " " + raterList.get(i).numRatings());
            ArrayList<String> ratedItems = raterList.get(i).getItemsRated(); // List of movies that are rated.
            
            // Iterate through the rated items list to find rating for each item
            for (int j = 0; j < ratedItems.size(); j++)
                System.out.println(ratedItems.get(j) + " " + raterList.get(i).getRating(ratedItems.get(j)));
        }
        */
    }
}
