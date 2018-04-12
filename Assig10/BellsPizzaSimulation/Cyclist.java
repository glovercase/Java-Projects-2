
/**
 * Write a description of class Cyclist here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cyclist implements Comparable<Cyclist>
{
    // instance variables - replace the example below with your own
    private int returnTime; //cyclsit return time from pizza run
    
    public void setReturnTime(int time){
        returnTime = time; //set new return time
    }
    
    public int getReturnTime(){
        return returnTime; //returns the cyclist time
    }
    
    public int compareTo(Cyclist other){
        return returnTime - other.returnTime; //comparsion 
    }
  
}
