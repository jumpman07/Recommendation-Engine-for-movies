
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import java.lang.*;

public class GenreFilter implements Filter {
    
    // A genre filter which helps to identify movies that are of a particular genre. It implements the interface Filter
    
    private String myGenre; 
    
    public GenreFilter(String genre){
        myGenre = genre;
    }
    
    public boolean satisfies(String id){
        
    // This call is made by the filterBy method in the MovieDatabase class
        String curGenre = MovieDatabase.getGenres(id);
        if (curGenre.indexOf(myGenre)!= -1)
            return true;
        
        return false;
    }
}
