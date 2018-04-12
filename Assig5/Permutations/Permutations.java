// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 5
 * Name:Casey Glover
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;

/** Prints out all permuations of a string
 *  The static method permute constructs all the permutations
 *  The main method gets the string, calls recPermute, and prints the result.
 */
public class Permutations {

    /** Return a List of all the permutations of a String. */
    public static List <String> recPermute(String string) {
        /*# YOUR CODE HERE */
        List<String> perm = new ArrayList<String>(); //new list
        if(string == ""){
            return perm; //base case
        }
        char first = string.charAt(0); //first character
        if(string.length() > 1){ 
            String rest = string.substring(1); //reduce the string 
            List<String> permList = recPermute(rest); //recursivly call the list
            for (String p : permList) { //for each string in the list
                for (int i = 0;i<=p.length();i++){
                    perm.add(insertChar(p, first, i)); //add to list
                }
            }

        }else{
            perm.add(first + ""); 
        }

        return  perm; //return list
    }

    public static String insertChar(String string, char middle, int j) {
        String front = string.substring(0, j); //fron of the string
        String back = string.substring(j); //back of string
        return front + middle + back; //return new postition 
    }

    // Main
    public static void main(String[] arguments){
        String string = "";
        while (! string.equals("#")) {
            string = UI.askString("Enter string to permute - # to exit: ");
            List<String> permutations = recPermute(string);
            for (String p : permutations)
                UI.println(p);
            UI.println("---------");
        }
        UI.quit();
    }    
}
