
/**
 * Write a description of class MinHeapArrayQueue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinHeapArrayQueue <E extends Comparable<? super E> > extends HeapArrayQueue <E>
{
   
    
    private boolean compare(int indexOne, int indexTwo) {
        return data[indexOne].compareTo(data[indexTwo]) > 0; //for the cyclist comparsions
    }
}
