// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP 103, Assignment 6
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

/** Code for Sorting Experiment
 *   - testing code
 *   - sorting algorithms
 *   - utility methods for creating, testing, printing, copying arrays
 */

public class Sorting implements UIButtonListener {
    private int count = 0; //for the trials
    private int size = 0;
    private String button = "Random"; //initial button type
    private String sortType = "Selection"; //initial sort type

    public Sorting(){
        UI.addButton("Random", this); //buttons
        UI.addButton("Sorted", this);
        UI.addButton("Reverse", this);
        UI.addButton("Quick Sort", this);
        UI.addButton("Merge Sort", this);
        UI.addButton("Quick Sort 2", this);
        UI.addButton("Selection Sort", this);
        UI.addButton("Selection Sort 2", this);
        UI.addButton("Insertion Sort", this);
        UI.addButton("Insertion Sort 2", this);
        UI.addButton("Merge Sort 2", this);
        UI.addButton("Quick Sort 3", this);
        UI.println("Select type of order then a type of Sort");
    }

    public void buttonPerformed(String button){

        if(button.equals("Random")){
            this.button = button; //setting the button
            UI.println("Your array is randomly sorted");
        }else if(button.equals("Sorted")){
            this.button = button;
            UI.println("Your array is sorted in order");
        }else if(button.equals("Reverse")){
            this.button = button;
            UI.println("Your array is sorted in reverse order");
        }else if(button.equals("Quick Sort")){          
            this.sortType = button; //setting the sort type
        }else if(button.equals("Merge Sort")){       
            this.sortType = button;       
        }else if(button.equals("Quick Sort 2")){         
            this.sortType = button;        
        }else if(button.equals("Selection Sort")){         
            this.sortType = button;         
        }else if(button.equals("Insertion Sort")){          
            this.sortType = button;        
        }else if(button.equals("Selection Sort 2")){        
            this.sortType = button;         
        }else if(button.equals("Insertion Sort 2")){         
            this.sortType = button;        
        }else if(button.equals("Merge Sort 2")){
            this.sortType = button;
        }else if(button.equals("Quick Sort 3")){
            this.sortType = button;
        }
        size = UI.askInt("Size of array?"); //ask for the size
        testSorts(size, this.button, this.sortType); //call the test sorts

    }

    /* Example method for testing and timing sorting algorithms.
     *  You will need to modify and extend this heavily to do your
     *  performance testing. It should probably run tests on each of the algorithms,
     *  on different sized arrays, and multiple times on each size.
     *  Make sure you create a new array each time you sort - it is not a good test if
     *  you resort the same array after it has been sorted.
     *  Hint: if you want to copy an array, use copyArray (below)
     */
    public void testSorts(int s, String sort, String order ){
        String[] data;
        int size = s; // eg 10000;
        long start;
        long time = 0;
        long total = 0;
        double average;
        // UI.printf("Number of items:  %,d\n", data.length);
        while(count < 10){ //loop ten times
            data = createArray(size); //create array
            if(order.equals("Reverse")){  
                Arrays.sort(data); //sort the array
                reverseArray(data); //reverse the array
            }else if(order.equals("Sorted")){
                Arrays.sort(data); //sort the array
            }

            if(sort.equals("Selection Sort")){
                UI.println("\n======Selection Sort=======\n");
                start = System.currentTimeMillis(); //record time
                selectionSort2(data); //selection sort
                time =  System.currentTimeMillis() - start;
                total += time; //add to total for working out average
            }else if(sort.equals("Quick Sort")){
                UI.println("\n======Quick Sort=======\n");
                start = System.currentTimeMillis();
                quickSort(data);
                time =  System.currentTimeMillis() - start;
                total += time;
            }else if(sort.equals("Quick Sort 2")){
                UI.println("\n======Quick Sort 2=======\n");
                start = System.currentTimeMillis();
                quickSort2(data);
                time =  System.currentTimeMillis() - start;
                total += time;
            }else if(sort.equals("Insertion Sort")){
                UI.println("\n======Insertion Sort=======\n");
                start = System.currentTimeMillis();
                insertionSort(data);
                time =  System.currentTimeMillis() - start;
                total += time;
            }else if (sort.equals("Merge Sort")){
                UI.println("\n======Merge Sort=======\n");
                start = System.currentTimeMillis();
                mergeSort(data);
                time =  System.currentTimeMillis() - start;
                total += time;
            }else if (sort.equals("Selection Sort 2")){
                UI.println("\n======Selection Sort2=======\n");
                start = System.currentTimeMillis();
                selectionSort2(data);
                time =  System.currentTimeMillis() - start;
                total += time;
            }else if(sort.equals("Insertion Sort 2")){
                UI.println("\n======Insertion Sort 2=======\n");
                start = System.currentTimeMillis();
                insertionSort2(data);
                time =  System.currentTimeMillis() - start;
                total += time;
                
            }else if (sort.equals("Merge Sort 2")){
                 UI.println("\n======Merge Sort=======\n");
                start = System.currentTimeMillis();
                mergeSort2(data);
                time =  System.currentTimeMillis() - start;
                total += time;
                
            }else if (sort.equals("Quick Sort 3")){
                UI.println("\n======Quick Sort 3=======\n");
                start = System.currentTimeMillis();
                quickSort3(data);
                time =  System.currentTimeMillis() - start;
                total += time;
                
            }

            //printData(data); //to check for sorted

            UI.printf("Sorted correctly: %b\n", testSorted(data));
            UI.printf("Time taken:       %.3f s\n", time/1000.0);
            UI.println("Count: "+count);

            UI.println("\n=======DONE=========");

            if(count == 10) return; //break out of loop

            count ++;

        }
        average = total/10; //calculate average
        UI.println("Average time :" +average); //print average
        count = 0; //set count back to 0

    }

    /* =============== SWAP ================= */
    /** Swaps the specified elements of an array.
     *  Used in several of the sorting algorithms
     */
    private  void swap(String[ ] data, int index1, int index2){
        if (index1==index2) return;
        String temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

    /* ===============SELECTION SORT================= */

    /** Sorts the elements of an array of String using selection sort */
    public  void selectionSort(String[ ] data){
        // for each position, from 0 up, find the next smallest item 
        // and swap it into place
        for (int place=0; place<data.length-1; place++){
            int minIndex = place;
            for (int sweep=place+1; sweep<data.length; sweep++){
                if (data[sweep].compareTo(data[minIndex]) < 0)
                    minIndex=sweep;
            }
            swap(data, place, minIndex);
        }
    }

    public  void selectionSort2(String[ ] data){
        // for each position, from 0 up, find the next smallest item 
        // and swap it into place
        for (int place=0; place<data.length-1; place++){
            int minIndex = place;
            int maxIndex = (data.length-1)-place; //decrement from the back

            for (int sweep=place+1; sweep<data.length; sweep++){
                if (data[sweep].compareTo(data[minIndex]) < 0){
                    minIndex=sweep;
                }
                if(data[sweep].compareTo(data[maxIndex]) < 0){
                    maxIndex=sweep; //compare from the back

                }
            }
            swap(data, place, minIndex);
            swap(data, (data.length-1)-place, maxIndex); //swap from the back
        }

    }

    /* ===============INSERTION SORT================= */
    /** Sorts the  elements of an array of String using insertion sort */
    public  void insertionSort(String[] data){
        // for each item, from 0, insert into place in the sorted region (0..i-1)
        for (int i=1; i<data.length; i++){
            String item = data[i];
            int place = i;
            while (place > 0  &&  item.compareTo(data[place-1]) < 0){
                data[place] = data[place-1];       // move up
                place--;
            }
            data[place]= item;
        }
    } 

    public  void insertionSort2(String[] data){
        for(int i = 1; i < data.length; i ++){
            String item = data[i];
            int low = 0;
            int high = i;

            while(low < high){
                int mid = (low+high)/2; //set the mid
                if(item.compareTo(data[mid]) > 0){ //binary search method
                    low = mid+1;

                }else if(item.compareTo(data[mid]) < 0){
                    high = mid;
                }else{
                    data[mid] = item;
                }

            }
            for(int j = i; j > low; j--){
                swap(data, j-1, j); //swap the items
            }

        }

    } 

    /* ===============MERGE SORT================= */
    /** non-recursive, wrapper method
     *  copy data array into a temporary array 
     *  call recursive mergeSort method     
     */
    public  void mergeSort(String[] data) {
        String[] other = new String[data.length];
        for(int i=0; i<data.length; i++)
            other[i]=data[i];
        mergeSort(data, other, 0, data.length); //call to recursive merge sort method
    }

    /** Recursive mergeSort method */
    public void mergeSort(String[] data, String[] temp, int low, int high) {
        if(low < high-1) {
            int mid = ( low + high ) / 2;
            mergeSort(temp, data, low, mid);
            mergeSort(temp, data, mid, high);
            merge(temp, data, low, mid, high);
        }
    }

    /** Merge method
     *  Merge from[low..mid-1] with from[mid..high-1] into to[low..high-1]
     *  Print data array after merge using printData
     */
    public void merge(String[] from, String[] to, int low, int mid, int high) {
        int index = low;      //where we will put the item into "to"
        int indxLeft = low;   //index into the lower half of the "from" range
        int indxRight = mid; // index into the upper half of the "from" range
        while (indxLeft<mid && indxRight < high) {
            if ( from[indxLeft].compareTo(from[indxRight]) <=0 )
                to[index++] = from[indxLeft++];
            else
                to[index++] = from[indxRight++];
        }
        // copy over the remainder. Note only one loop will do anything.
        while (indxLeft<mid)
            to[index++] = from[indxLeft++];
        while (indxRight<high)
            to[index++] = from[indxRight++];
    }
    
    public  void mergeSort2(String[] data) {
        String[] other = new String[data.length];
        for(int i=0; i<data.length; i++)
            other[i]=data[i];
        merge2(data, other, 0, data.length); //call to recursive merge sort method
    }
    
    public void merge2(String[] data, String[] temp, int low, int high){
      
        for(int range = 1; range<high; range = range*2) { 
           for(int start = 0; start<high; start = start+2*range) { 
               int end = Math.min(start+2*range, size); //check to see which is smaller
               int mid = Math.min(start+range, size); //check to see which is smaller
               merge(temp, data, start, mid, end); //call merge
            }
        }
        
    }

    /*===============QUICK SORT=================*/
    /** Sort data using QuickSort
     *  Print time taken by Quick sort
     *  Print number of times partition gets called
     */

    /** Quick sort recursive call */
    public  void quickSort(String[ ] data) {
        quickSort(data, 0, data.length);
    }

    public  void quickSort(String[ ] data, int low, int high) {
        if (high-low < 2)      // only one item to sort.
            return;
        else {     // split into two parts, mid = index of boundary
            int mid = partition(data, low, high);
            quickSort(data, low, mid);
            quickSort(data, mid, high);
        }
    }

    /** Partition into small items (low..mid-1) and large items (mid..high-1) 
     *  Print data array after partition
     */
    private int partition(String[] data, int low, int high) {
        String pivot = data[(low+high)/2];
        int left = low-1;
        int right = high;
        while( left < right ) {
            do { 
                left++;       // just skip over items on the left < pivot
            } 
            while (left<high && data[left].compareTo(pivot) < 0);

            do { 
                right--;     // just skip over items on the right > pivot
            } 
            while (right>=low &&data[right].compareTo(pivot) > 0);

            if (left < right) 
                swap(data, left, right);
        }
        return left;
    }

    /** Quick sort, second version:  simpler partition method
     *   faster or slower?  */
    public  void quickSort2(String[ ] data) {
        quickSort2(data, 0, data.length);
    }

    public  void quickSort2(String[ ] data, int low, int high) {
        if (low+1 >= high) // no items to sort.
            return;
        else {     // split into two parts, mid = index of pivot
            int mid = partition2(data, low, high);
            quickSort2(data, low, mid);
            quickSort2(data, mid+1, high);
        }
    }

    public int partition2(String[] data, int low, int high){
        swap(data, low, (low+high)/2);    // choose pivot and put at position low
        String pivot = data[low];
        int mid = low;
        for(int i = low+1; i < high; i++){  // for each item after the pivot
            if ( data[i].compareTo(pivot) <0 ){
                mid++;                      // move mid point up
                swap(data, i, mid);
            }
        }
        swap(data, low, mid);   // move pivot to the mid point
        return mid;
    }
    
    
    public  void quickSort3(String[ ] data) {
        quickSort3(data, 0, data.length-1); 
    }

    public  void quickSort3(String[ ] data, int low, int high) {
        if (low+10 >= high) // no items to sort.
            insertionSort(data); //faster to use insertion sort
        else {     // split into two parts, mid = index of pivot
            int med = median(data, low, high); //setting the median
            int part = partition3(data, low, high, med); //partiontiong
            quickSort3(data, low, part-1);
            quickSort3(data, part+1, high);
        }
    }
    
    public int median(String[] data, int low, int high){
        int mid = (low + high)/2;
            if(data[low].compareTo(data[mid])>0){
               swap(data, low, mid);//swapping low with mid
            }
            if(data[low].compareTo(data[high])>0){
                swap(data, low, high);//swapping low with high
            }
            if(data[mid].compareTo(data[high])>0){
               swap(data, mid, high); //swapping mid with high
            }
            swap(data, mid, high);
        return high; //retruning the median
    }

    public int partition3(String[] data, int low, int high, int piv){
         // choose pivot and put at position low
        String pivot = data[piv]; //set the pivot
        int mid = low;
        for(int i = low+1; i < high; i++){  // for each item after the pivot
            if ( data[i].compareTo(pivot) <0 ){
                mid++;                      // move mid point up
                swap(data, i, mid);
            }
        }
        swap(data, low, mid);   // move pivot to the mid point
        return mid;
    }


    /* =====   Utility methods ============================================ */
    /** Tests whether an array is in sorted order
     */
    public boolean testSorted(String[] data) {
        for (int i=1; i<data.length; i++){
            if (data[i].compareTo(data[i-1]) < 0)
                return false;
        }
        return true;
    }

    public void printData(String[] data){
        for (String str : data){
            UI.println(str);
        }
    }

    /** Constructs an array of Strings by making random String values */
    public String[] createArray(int size) {

        Random randGenerator = new Random();
        String[] data = new String[size];
        for (int i=0; i<size; i++){
            char[] chars = new char[5];
            for (int c=0; c<chars.length; c++){
                chars[c] = (char) ('a' + randGenerator.nextInt(26));
            }
            String str = new String(chars);
            data[i] = str;
        }
        
        
       // String[] data = new String[]{"b", "c", "a", "d"}; //quick check to see if it sorts
        
        return data;

    }
    /** Constructs an array of Strings by reading a file
     * The size of the array will be the specified size, unless the
     * file is too short, or size is -ve, in which cases the array will
     * contain all the tokens in the file. */
    public String[] readArrayFromFile(int size, String filename) {
        File file = new File(filename);
        if (!file.exists()){
            UI.println("file "+filename+" does not exist");
            return null;
        }
        List<String> temp = new ArrayList<String> ();
        try {
            Scanner scan = new Scanner(new File(filename));
            while (scan.hasNext()) 
                temp.add(scan.next());
            scan.close();
        }
        catch(IOException ex) {   // what to do if there is an io error.
            UI.println("File reading failed: " + ex);
        }
        if (temp.size() < size || size<0)
            size = temp.size();

        String[] data = new String[size];
        for (int i =0; i<size; i++){
            data[i] = temp.get(i);
        }
        return data;
    }

    /** Create a new copy of an array of data */
    public String[] copyArray(String[] data){
        String[] newData = new String[data.length];
        for (int i=0; i<data.length; i++){
            newData[i] = data[i];
        }
        return newData;
    }

    /** Create a new copy of the first size elements of an array of data */
    public String[] copyArray(String[] data, int size){
        if (size> data.length) size = data.length;
        String[] newData = new String[size];
        for (int i=0; i<size; i++){
            newData[i] = data[i];
        }
        return newData;
    }

    public void reverseArray(String[] data){
        int bot = 0;
        int top = data.length-1;
        while (bot<top){
            swap(data, bot++, top--);
        }
    }

    public static void main(String[] args){
        new Sorting(); // new sorting method
    }

}
