// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 4
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/** SpellingChecker checks all the words in a file against a dictionary.
 *  It prints out (one per line) any word in the file that is not in the dictionary.
 *  The program can be very simple - it is OK to just have a main method
 *  that contains all the code, but you can use multiple methods if you want.
 *  
 *  The key requirement is that the dictionary should be read from the
 *  dictionary file into a set.
 *  When reading through the document with spelling errors, the program should
 *  check each each word against the dictionary.
 *  Any word that is not in the dictionary is considered to be an error and
 *  should be printed out.
 *
 *  The program should record and print out the total time taken to read
 *  all the words into the dictionary.
 *  It should also record and print out the total time taken to check all the words.
 *
 *  Note that the dictionary and the file to check are assumed to be all
 *  lowercase, with all punctuation removed.
 */

public class SpellingChecker implements UIButtonListener{

    // Suggested Design:
    // 
    // 1. create a Set of String to hold the words in the dictionary.
    //    (For your first version, use a HashSet; Then use your ArraySet.
    //
    // 2. 
    //   read each word in the file "dictionary.txt" (use a Scanner)
    //   and add each word to the dictionary Set.
    //   Note that the dictionary contains just over 200,000 words
    //
    // 3. 
    //   Ask the user (UIFileChooser) for the file to check
    //   open the file in a Scanner.
    //   record the start time
    //   loop through file reading each word.
    //   check if the word is in dictionary set
    //   if not, print out the word.
    //   print out total time taken

    /*# YOUR CODE HERE */
    private Set<String> dict = new HashSet<String>();
    private Set<String> single = new HashSet<String>();
    private String word;
    private String checkWord;
    private ArraySet<String> dictionary = new ArraySet<String>();
    private ArraySet<String> singleSet = new ArraySet<String>();

    public SpellingChecker(){
        //stuff?
        UI.addButton("Hash Set", this);
        UI.addButton("Array Set", this);
        getTextHashSet();
        getTextArraySet();

    }

    public void buttonPerformed(String button){
        if(button.equals("Hash Set")){
            runHashSet(); 
        }else if(button.equals("Array Set")){
            runArraySet();
        }
    }

    public void getTextHashSet(){
        try{
            Scanner scan = new Scanner(new File("dictionary.txt"));  
            long startTime = System.currentTimeMillis();
            while(scan.hasNext()){
                word = scan.next();

                dict.add(word);
            }
            long endTime = System.currentTimeMillis();
            UI.println("Time to load Hash Set dictionary :"+(endTime - startTime));
            scan.close();
        }catch(IOException e){UI.println("Fail: " + e);}
    }

    public void getTextArraySet(){
        try{
            Scanner scan = new Scanner(new File("dictionary.txt"));  
            long startTime = System.currentTimeMillis();
            while(scan.hasNext()){
                word = scan.next();
                dictionary.add(word);  
            }
            long endTime = System.currentTimeMillis();
            UI.println("Time to load Array Set dictionary :"+(endTime - startTime));
            scan.close();
        }catch(IOException e){UI.println("Fail: " + e);}
    }

    public void runHashSet(){
        try{
            Scanner scan = new Scanner(new File(UIFileChooser.open()));
            long startTime = System.currentTimeMillis();//record start time
            while(scan.hasNext()){
                checkWord = scan.next();
                checkWord = checkWord.replaceAll("[^a-zA-Z ]", "").toLowerCase();
                if(!dict.contains(checkWord)){
                    single.add(checkWord);     
                }
            }
            for(String s: single){
                UI.println(s);
            }
            long endTime = System.currentTimeMillis();
            UI.println("Time: "+(endTime - startTime));
        }catch(IOException e){UI.println("Fail: "+e);}
    }

    public void runArraySet(){
        try{
            Scanner scan = new Scanner(new File(UIFileChooser.open()));
            long startTime = System.currentTimeMillis();//record start time
            while(scan.hasNext()){
                checkWord = scan.next();
                checkWord = checkWord.replaceAll("[^a-zA-Z ]", "").toLowerCase();
                if(!dictionary.contains(checkWord)){
                    singleSet.add(checkWord);
                }

            }
            for(String s: singleSet){
                UI.println(s);
            }
            
            long endTime = System.currentTimeMillis();
            UI.println("Time: "+(endTime - startTime));
        }catch(IOException e){UI.println("Fail: "+e);}
    }

    public static void main(String[] args){
        new SpellingChecker();
    }

}
