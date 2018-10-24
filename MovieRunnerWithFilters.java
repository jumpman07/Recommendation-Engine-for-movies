
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class MovieRunnerWithFilters {
    
    public void printAverageRatings(){
    // Prints general info on the movies and ratings
        
        ThirdRatings thirdRat = new ThirdRatings("ratings.csv"); // Create a SecondRatings object
        System.out.println("Number of raters: "+thirdRat.getRaterSize());
        
        // Fire the moviedatabase
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        // Print number of movies in database
        System.out.println("Number of movies are: " + MovieDatabase.size());
        
        // Print ratings in order
        int minRaters = 35;
        ArrayList<Rating> ratingsList = thirdRat.getAverageRatings(minRaters);
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
    
    public void printAverageRatingsByYear(){
        
        // Prints the movies released after a specific year. Implements the YearAfterFilter
        
        ThirdRatings thirdRat = new ThirdRatings("ratings.csv"); // Create a ThirdRatings object
        System.out.println("read data for " + thirdRat.getRaterSize());
        
        // Fire the moviedatabase
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        // Print number of movies in database
        System.out.println("read data for " + MovieDatabase.size());
        
        Filter f = new YearAfterFilter(2000);     // Create a Filter object f of type YearAfterFilter
        
        int minRaters = 20;
        ArrayList<Rating> movieList = thirdRat.getAverageRatingsByFilter(minRaters,f);
        Collections.sort(movieList);    // Sort the rating list
        
        System.out.println("found " + movieList.size() + " movies");
        
        /*
        if (movieList != null){
            for (int i = 0; i < movieList.size(); i++){
                double rating = movieList.get(i).getValue();
                String id = movieList.get(i).getItem();
                String title = MovieDatabase.getTitle(id); // Get the title of the movie from its id using the getTitle() method in MovieDatabase class
                if (!title.equals("not found") && rating > 0.0){
                    System.out.println(rating + "  " + MovieDatabase.getYear(id) + "  " + title);
                }
            }
        }
        */
    }
    
    public void printAverageRatingsByGenre(){
        
        // Prints the movies of a particular genre
        ThirdRatings thirdRat = new ThirdRatings("ratings.csv"); // Create a ThirdRatings object
        System.out.println("read data for " + thirdRat.getRaterSize());
        
        // Fire the moviedatabase
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        // Print number of movies in database
        System.out.println("read data for " + MovieDatabase.size());
        
        Filter f = new GenreFilter("Comedy");   // Create a Filter object f of type GenreFilter
        
        int minRaters = 20;
        ArrayList<Rating> movieList = thirdRat.getAverageRatingsByFilter(minRaters,f);
        Collections.sort(movieList);    // Sort the rating list
        
        System.out.println("found " + movieList.size() + " movies");
        /*
        if (movieList != null){
            for (int i = 0; i < movieList.size(); i++){
                double rating = movieList.get(i).getValue();
                String id = movieList.get(i).getItem();
                String title = MovieDatabase.getTitle(id); // Get the title of the movie from its id using the getTitle() method in MovieDatabase class
                if (!title.equals("not found") && rating > 0.0){
                    System.out.println(rating + "  " + "  " + title + "  " + MovieDatabase.getGenres(id));
                }
            }
        }
        */
    }
    
    public void printAverageRatingsByMinutes(){
        
        // Prints the movies of a particular genre
        ThirdRatings thirdRat = new ThirdRatings("ratings.csv"); // Create a ThirdRatings object
        System.out.println("read data for " + thirdRat.getRaterSize() + " raters");
        
        // Fire the moviedatabase
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        // Print number of movies in database
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        Filter f = new MinutesFilter(105,135);   // Create a Filter object f of type GenreFilter
        
        int minRaters = 5;
        ArrayList<Rating> movieList = thirdRat.getAverageRatingsByFilter(minRaters,f);
        Collections.sort(movieList);    // Sort the rating list
        
        System.out.println("found " + movieList.size() + " movies");
        
        /*
        if (movieList != null){
            for (int i = 0; i < movieList.size(); i++){
                double rating = movieList.get(i).getValue();
                String id = movieList.get(i).getItem();
                String title = MovieDatabase.getTitle(id); // Get the title of the movie from its id using the getTitle() method in MovieDatabase class
                if (!title.equals("not found") && rating > 0.0){
                    System.out.println(rating + "  " + "  " +  "Time: " + MovieDatabase.getMinutes(id) + "  " + title);
                }
            }
        }
        */
    }
    
    public void printAverageRatingsByDirectors(){
        
         // Prints the movies of a particular genre
        ThirdRatings thirdRat = new ThirdRatings("ratings.csv"); // Create a ThirdRatings object
        System.out.println("read data for " + thirdRat.getRaterSize() + " raters");
        
        // Fire the moviedatabase
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        // Print number of movies in database
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        Filter f = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        
        int minRaters = 4;
        ArrayList<Rating> movieList = thirdRat.getAverageRatingsByFilter(minRaters,f);
        Collections.sort(movieList);    // Sort the rating list
        
        System.out.println("found " + movieList.size() + " movies");
        /*
        if (movieList != null){
            for (int i = 0; i < movieList.size(); i++){
                double rating = movieList.get(i).getValue();
                String id = movieList.get(i).getItem();
                String title = MovieDatabase.getTitle(id); // Get the title of the movie from its id using the getTitle() method in MovieDatabase class
                if (!title.equals("not found") && rating > 0.0){
                    System.out.println(rating + "  "  + title + "  " + MovieDatabase.getDirector(id));
                }
            }
        }
        */
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
       

         // Prints the movies of a particular genre
        ThirdRatings thirdRat = new ThirdRatings("ratings.csv"); // Create a ThirdRatings object
        System.out.println("read data for " + thirdRat.getRaterSize() + " raters");
        
        // Fire the moviedatabase
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        // Print number of movies in database
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        Filter fYearAfter = new YearAfterFilter(1990);
        Filter fGenre     = new GenreFilter("Drama");
       
        // Create an AllFilters object and add the yearAfterFilter and genreFilter to it.
       AllFilters f = new AllFilters();
       f.addFilter(fYearAfter);
       f.addFilter(fGenre);
       
       int minRaters = 8;
        ArrayList<Rating> movieList = thirdRat.getAverageRatingsByFilter(minRaters,f);
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
    
    public void  printAverageRatingsByDirectorsAndMinutes(){
        
         // Prints the movies of a particular genre
        ThirdRatings thirdRat = new ThirdRatings("ratings.csv"); // Create a ThirdRatings object
        System.out.println("read data for " + thirdRat.getRaterSize() + " raters");
        
        // Fire the moviedatabase
        MovieDatabase.initialize("ratedmoviesfull.csv");
        
        // Print number of movies in database
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        Filter fDirectors = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        Filter fMinutes   = new MinutesFilter(90,180);
        
         // Create an AllFilters object and add the yearAfterFilter and genreFilter to it.
       AllFilters f = new AllFilters();
       f.addFilter(fDirectors);
       f.addFilter(fMinutes);
       
       int minRaters = 3;
        ArrayList<Rating> movieList = thirdRat.getAverageRatingsByFilter(minRaters,f);
        Collections.sort(movieList);    // Sort the rating list
        
        System.out.println(movieList.size() + " movie matched");
       
        /*
       if (movieList != null){
            for (int i = 0; i < movieList.size(); i++){
                double rating = movieList.get(i).getValue();
                String id = movieList.get(i).getItem();
                String title = MovieDatabase.getTitle(id); // Get the title of the movie from its id using the getTitle() method in MovieDatabase class
                if (!title.equals("not found") && rating > 0.0){
                    System.out.println(rating + "  " + "  " +  "Time: " + MovieDatabase.getMinutes(id) + "  " + title + " " + MovieDatabase.getDirector(id));
                }
            }
        }
        */
        
    }
}
