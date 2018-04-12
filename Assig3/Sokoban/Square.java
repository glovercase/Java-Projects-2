// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.


/* Code for COMP 103 Assignment 3  */
import java.util.*;

/**  The possible squares, along with useful methods.
     Would be better represented by an Enum class.
*/

public enum Square{
    
    
    empty("empty"), wall("wall"), box("box"), shelf("shelf"), boxOnShelf("boxOnShelf"); 
    
    private String type;

    private Square(String t){
	type = t;
    }
 
    /** Whether there is a box on this square */
    public boolean hasBox() {
	return (this==box|| this==boxOnShelf);
    }

    /** Whether the square is free to move onto */
    public boolean free() {
	return (this==empty || this==shelf);
    }

    /** The square you get if you push a box off this square */
    public Square moveOff() {
	if (this==box) return empty;
	if (this==boxOnShelf)  return shelf;
	return this;
    }
    
    
    //public boolean target(){
        //
        
    //}

    /** The square you get if you push a box on to this square */
    public Square moveOn() {
	if (this==empty) return box;
	if (this==shelf) return boxOnShelf;
	return this;
    }
    
}
