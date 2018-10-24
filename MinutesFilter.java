
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MinutesFilter implements Filter {
    
    private int minMinutes;
    private int maxMinutes;
    
    public MinutesFilter(int minTime, int maxTime){
        minMinutes = minTime;
        maxMinutes = maxTime;
    }
    
    public boolean satisfies(String id){
        
        return MovieDatabase.getMinutes(id) >= minMinutes && MovieDatabase.getMinutes(id) <= maxMinutes;
    }

}
