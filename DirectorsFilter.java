
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class DirectorsFilter implements Filter{
    
    private String[] myDirectors;
    
    public DirectorsFilter(String directors){
        myDirectors = directors.split(",");
    }
    
    public boolean satisfies(String id){
        
        for (int i = 0; i < myDirectors.length; i++){
            
            if (MovieDatabase.getDirector(id).contains(myDirectors[i]))
                return true;
        }
        
        return false;
    }
}
