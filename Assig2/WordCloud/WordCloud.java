// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 2
 * Name: Casey Glover
 * Usercode:
 * ID: 300280613
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;
import java.io.*;

/** This program reads 2 text files and compiles word counts for each.
 *  It then eliminates rare words, and words that only occur in one
 *  document, and displays the remainder as a "word cloud" on a graphics pane,
 *  to allow the user to examine differences between the word usage in
 *  the two documents.
 */ 
public class WordCloud implements UIButtonListener {

    // Fields:
    private int numWordsToRemove = 100;

    // The two maps.
    private Map <String,Double> counts1, counts2;
    

    // Constructor
    /** Constructs a WordCloud object
     *  Set up the graphical user interface, and call the basic method.
     */ 
    public WordCloud() {
        // Set up the GUI.
        UI.addButton("remove standard common words", this);
        UI.addButton("remove infrequent words", this);
        UI.addButton("remove un-shared words", this);

        String fname1 = UIFileChooser.open("First filename to read text from");
        counts1 = buildHistogram(fname1);
        UI.println("Text read from " + fname1);

        String fname2 = UIFileChooser.open("Second filename to read text from");
        counts2 = buildHistogram(fname2);
        UI.println("Text read from " + fname2);

        displayWords();
    }

    /** Read the contents of a file, counting how often each word occurs.
     *  Put the counts (as Doubles) into a Map, which is returned.
     *  [CORE]
     */
    public Map <String, Double> buildHistogram(String filename) {
        if (filename == null) return null;
        Map <String,Double> wordcounts;
        double total = 0.0;
        try {
            // Open the file and get ready to read from it
            Scanner scan = new Scanner(new File(filename));

            // The next line tells Scanner to remove all punctuation
            scan.useDelimiter("[^-a-zA-Z']"); 

            wordcounts = new HashMap <String,Double> ();
            /*# YOUR CODE HERE */
            while(scan.hasNext()){
                String word = scan.next();
                if(wordcounts.containsKey(word)){
                    wordcounts.put(word, wordcounts.get(word)+1);
                }else{
                    wordcounts.put(word, 1.0);
                }
            }
            scan.close(); // closes the scanner 
            return wordcounts;
        }
        catch(IOException ex) {
            UI.println("Could not read the file " + ex.getMessage());
            return null;
        }
    }

    /** Construct and return a Set of all the words that occur in EITHER
     *  document.
     *  [CORE]
     */
    public Set <String> findAllWords() {
        /*# YOUR CODE HERE */
        Set <String> words = new HashSet<String>();
        for(String key : counts1.keySet()){
            words.add(key);
        }
        for(String key : counts2.keySet()){
            words.add(key);
        }
        return words; 
    }

    /** Display words that exist in both documents.
     *  
     *  The x-position is essentially random (it just depends on the order in
     *  which an iterator goes through a Set).
     *  
     *  However the y-position reflects how much the word is used in the 1st
     *  document versus the 2nd. That is, a word that is common in the 1st and
     *  uncommon in the second should appear at the top.
     *  
     *  The SIZE of the word as displayed reflects how common the word is
     *  overall, including its count over BOTH documents.
     *  NB! There is UI.setFontSize method that may come in useful!
     *  
     *  [CORE]
     */
    public void displayWords() {

        UI.clearGraphics();
        UI.setImmediateRepaint(false);

        if ((counts1 == null) || (counts2 == null)) return;
        // First we re-normalise the counts.
        normaliseCounts(counts1);
        normaliseCounts(counts2);
        double c1;
        double c2;
        double x;
        double y;
        for(String s: findAllWords()){
            y = Math.random() * 500;
            if(counts1.get(s) != null && counts2.get(s) != null){
                c1 = counts1.get(s);
                c2 = counts2.get(s);
                setFont(c1);
                x = (0.9 * c1/(c1+c2)) * 500;
                UI.setColor(Color.black);
                UI.drawString(s, x, y);
            } else if(counts1.get(s) == null && counts2.get(s) != null){
                x = 10; 
                c2 = counts2.get(s);
                UI.setColor(Color.red);
                UI.drawString(s, x, y);

            } else if(counts1.get(s) != null && counts2.get(s) == null){
                x = 500;
                c1 = counts1.get(s);
                UI.setColor(Color.blue);
                UI.drawString(s, x, y);
            } 
        }

        UI.repaintGraphics();

        return;
    }

    public void setFont(double size){
        double font = size * 200;
        int f = (int)font;
        if(f < 10){
            f = 10;
        }
        UI.setFontSize(f);
    }

    /** Take a word count Map, and a Set of words. Remove those words from the
     *  Map.
     *  [COMPLETION]
     */
    public void removeWords(Map<String,Double> wc, Set<String> words) {
        /*# YOUR CODE HERE */
        for(String s: wc.keySet()){
            if(words.contains(s)){
                words.remove(s); 
            }
        }
    }

    /** Takes a Map from strings to integers, and an integer,
     * limitNumWords. It should leave this Map containing only the
     * limitNumWords most common words in the original.
     * [COMPLETION]
     */
    public void removeInfrequentWords (Map<String,Double> c, int limitNumWords) 
    {
        /*# YOUR CODE HERE */
        int toLimit = 0;
        ValueComparator compareWords =  new ValueComparator(c);
        Map<String, Double> orderedWords = new TreeMap<String, Double>(compareWords);
        Map<String, Double> topWords = new TreeMap<String, Double>();
        for(String s: orderedWords.keySet()){
            if(toLimit < limitNumWords){
                topWords.put(s, topWords.get(s));
                toLimit ++;
            } else{
                break; 
            }

        }

    }

    /** Take a Map from words to counts, and "normalise" the counts,
     *  so that they are fractions of the total: they should sum to one.
     */
    public void normaliseCounts(Map <String, Double> counts) {
        // Figure out the total in the current Map
        if (counts == null) return;
        double total = 0.0;
        for (String wd : counts.keySet()) 
            total += counts.get(wd);

        // Divide all values by the total, so they will sum to one.
        for (String wd : counts.keySet()) {
            double count = counts.get(wd)/total;
            counts.put(wd,count);
        }
    }

    /** Print the words and their counts to standard out.
     *  Not necessary to the program, but might be useful for debugging
     */
    public void printcounts(Map <String,Double> counts ) {
        if (counts == null) {
            UI.println("The Map is empty");
            return;
        }
        for (String s : counts.keySet()) 
            UI.printf("%15s \t : \t %.3f \n",s,counts.get(s));
        UI.println("----------------------------------");
    }

    //-- GUI stuff --------------------------------------------------------
    /** Respond to button presses */
    public void buttonPerformed(String button) {

        if (button.equals("remove standard common words")) {
            String fname = "some-common-words.txt"; // More general form: UIFileChooser.open("filename to read common words from");
            if (fname == null) return;
            UI.println("Getting ignorable words from " + fname);

            // Set the elements of the toRemove Set to be the words in file
            try {
                Set <String> toRemove = new HashSet <String> ();
                Scanner scan = new Scanner(new File(fname));
                while (scan.hasNext()) {
                    String str = scan.next().toLowerCase().trim(); 
                    toRemove.add(str);
                }
                scan.close();

                // Remove the words
                removeWords(counts1, toRemove);
                removeWords(counts2, toRemove);
            }
            catch(IOException ex) {   // what to do if there is an io error.
                UI.println("Could not read the file " + ex.getMessage());
            }
        }

        else if (button.equals("remove infrequent words") ) {
            UI.println("Keeping only the most common " + numWordsToRemove 
                + " words");
            removeInfrequentWords(counts1,numWordsToRemove);
            removeInfrequentWords(counts2,numWordsToRemove);
            numWordsToRemove = numWordsToRemove/2; // It halves each time.
        }

        else if (button.equals("remove un-shared words") ) {
            UI.println("Keeping only words that occur in BOTH docs ");
            Set <String> wordsToBeRemoved = new HashSet <String> ();
            for (String wd : counts1.keySet()) 
                if (!counts2.keySet().contains(wd)) wordsToBeRemoved.add(wd);
            for (String wd : counts2.keySet()) 
                if (!counts1.keySet().contains(wd)) wordsToBeRemoved.add(wd);
            // Notice you do need to do both!
            // Now actually remove them.
            removeWords(counts1, wordsToBeRemoved);
            removeWords(counts2, wordsToBeRemoved);
        }

        // printcounts(counts1);
        // printcounts(counts2);

        // Now redo everything on the screen
        displayWords();
    }

    //================================================================
    // Main
    public static void main(String[] args) {
        new WordCloud();
    }
}

class ValueComparator implements Comparator<String> {
    //reading from stack overflow i looked up how to sort a map
    Map<String, Double> word;
    public ValueComparator(Map<String, Double> word) {
        this.word = word;
    }

    public int compare(String a, String b) {
        if(word.get(a) >= word.get(b)){
            return 1; 
        }else if (word.get(a) == word.get(b)){ 
            return 0;
        }else{
            return -1;
        }
    }
}
