import ecs100.*;
import java.util.*;
/**
 * Write a description of class main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class Testing {

    public static void main(String[] args) {
        
        int []array = new int[]{1,2,4,5,6,7};
        int item = 3;
        

        int index = Arrays.binarySearch(array,0, 1 ,item);
        
        UI.println(index);
    }
}

class ValueComparator implements Comparator<String> {

    Map<String, Double> base;
    public ValueComparator(Map<String, Double> base) {
        this.base = base;
    }

    // Note: this comparator imposes orderings that are inconsistent with equals.    
    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } // returning 0 would merge keys
    }
}


