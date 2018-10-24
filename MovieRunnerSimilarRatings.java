
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.*;

public class MovieRunnerSimilarRatings {
    
    public void printAverageRatings(){
        
        // Fire the RaterDatabase class and print the number of raters in the file
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
            
        // Fire the moviedatabase and Print number of movies in database
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are: " + MovieDatabase.size());
        
        // Print ratings in order
        int minRaters = 35;
        FourthRatings fourthRat = new FourthRatings(); // Create a new object of FourthRatings class
        ArrayList<Rating> ratingsList = fourthRat.getAverageRatings(minRaters);
        Collections.sort(ratingsList);  // Sort the ratingsList
        
        int count = 0;
        if (ratingsList != null){
            for (int i = 0; i < ratingsList.size(); i++){
                double rating = ratingsList.get(i).getValue();
                String id = ratingsList.get(i).getItem();
                String title = MovieDatabase.getTitle(id); // Get the title of the movie from its id using the getTitle() method in MovieDatabase class
                if (!title.equals("not found") && rating > 0.0){
                    System.out.println(rating + "  " + title);
                    count++;
                }
            }
        }
        
        System.out.println("More than " + minRaters + " raters: " + count);
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        
        // Fire the RaterDatabase class and print the number of raters in the file
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
            
        // Fire the moviedatabase and Print number of movies in database
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Number of movies are: " + MovieDatabase.size());
        
        // Create a yearAfterFilter and GenreFilter object
        Filter fYearAfter = new YearAfterFilter(1990);
        Filter fGenre     = new GenreFilter("Drama");
       
       // Create an AllFilters object and add the yearAfterFilter and genreFilter to it.
       AllFilters f = new AllFilters();
       f.addFilter(fYearAfter);
       f.addFilter(fGenre);
       
       int minRaters = 8;
       FourthRatings fourthRat = new FourthRatings(); // Create a new object of class FourthRatings
       ArrayList<Rating> movieList = fourthRat.getAverageRatingsByFilter(minRaters,f);
       Collections.sort(movieList);    // Sort the rating list
        
       System.out.println(movieList.size() + " movie matched");
       /* 
       if (movieList != null){
            for (int i = 0; i < movieList.size(); i++){
                double rating = movieList.get(i).getValue();
                String id = movieList.get(i).getItem();
                String title = MovieDatabase.getTitle(id); // Get the title of the movie from its id using the getTitle() method in MovieDatabase class
                if (!title.equals("not found") && rating > 0.0){
                    System.out.println(rating + "  "  + MovieDatabase.getYear(id) + "  " + title + "  " + MovieDatabase.getGenres(id));
                }
            }
        }
        */
    }
    
    public void printSimilarRatings(){
        
        /*
         * Prints recommended movies with their title and their similarity ratings
         */
        
        FourthRatings fourthRat = new FourthRatings(); // Create a FourthRatings object
        
        // Fire the RaterDatabase class and print the number of raters in the file
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
            
        // Fire the moviedatabase and Print number of movies in database
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        // Get similar ratings to a particular raterID
        ArrayList<Rating> recMovies = fourthRat.getSimilarRatings("337",10,3);
        System.out.println("There are " + recMovies.size() + " recommended movies");
        
        // Print the top rating
        Rating rating = recMovies.get(0);
        String movieTitle = MovieDatabase.getTitle(rating.getItem());
        System.out.println(movieTitle + " " + rating.getValue());
       
    }
    
    public void printSimilarRatingsByGenre(){
        
        FourthRatings fourthRat = new FourthRatings(); // Create a FourthRatings object
        
        // Fire the RaterDatabase class and print the number of raters in the file
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
            
        // Fire the moviedatabase and Print number of movies in database
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        // Get similar ratings to a particular raterID and of a particular genre
        ArrayList<Rating> recMovies = fourthRat.getSimilarRatingsByFilter("964",20,5, new GenreFilter("Mystery"));
        System.out.println("There are " + recMovies.size() + " recommended movies");
        
        // Print the top rating
        Rating rating = recMovies.get(0);
        String movieTitle = MovieDatabase.getTitle(rating.getItem());
        System.out.println(movieTitle + " " + rating.getValue());
    }
    
    public void printSimilarRatingsByDirector(){
      
        FourthRatings fourthRat = new FourthRatings(); // Create a FourthRatings object
        
        // Fire the RaterDatabase class and print the number of raters in the file
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
            
        // Fire the moviedatabase and Print number of movies in database
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        // Get similar ratings to a particular raterID and of a particular genre
        ArrayList<Rating> recMovies = fourthRat.getSimilarRatingsByFilter("120",10,2, new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));
        System.out.println("There are " + recMovies.size() + " recommended movies");
        
        // Print the top rating
        Rating rating = recMovies.get(0);
        String movieTitle = MovieDatabase.getTitle(rating.getItem());
        System.out.println(movieTitle + " " + rating.getValue());
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        
        FourthRatings fourthRat = new FourthRatings(); // Create a FourthRatings object
        
        // Fire the RaterDatabase class and print the number of raters in the file
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
            
        // Fire the moviedatabase and Print number of movies in database
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        // Create an AllFilters object and add genre and minutes filter to it
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter("Drama"));
        f.addFilter(new MinutesFilter(80,160));
        
        // Get similar ratings to a particular raterID and of a particular genre
        ArrayList<Rating> recMovies = fourthRat.getSimilarRatingsByFilter("168",10,3,f);
        System.out.println("There are " + recMovies.size() + " recommended movies");
        
        // Print the top rating
        Rating rating = recMovies.get(0);
        String movieTitle = MovieDatabase.getTitle(rating.getItem());
        System.out.println(movieTitle + " " + rating.getValue());
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        
         FourthRatings fourthRat = new FourthRatings(); // Create a FourthRatings object
        
        // Fire the RaterDatabase class and print the number of raters in the file
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
            
        // Fire the moviedatabase and Print number of movies in database
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        // Create an AllFilters object and add genre and minutes filter to it
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(70,200));
        f.addFilter(new YearAfterFilter(1975));
        
        // Get similar ratings to a particular raterID and of a particular genre
        ArrayList<Rating> recMovies = fourthRat.getSimilarRatingsByFilter("314",10,5,f);
        System.out.println("There are " + recMovies.size() + " recommended movies");
        
        // Print the top rating
        Rating rating = recMovies.get(0);
        String movieTitle = MovieDatabase.getTitle(rating.getItem());
        System.out.println(movieTitle + " " + rating.getValue());
        
    } 
}
