// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.


/* Code for COMP 103, Assignment 5
 * Name: Casey Glover
 * Usercode:
 * ID:
 */
 
import java.util.*;

/**
 * SortedArraySet - a Set collection;
 *
 * The implementation uses an array and a count to store the items.
 *  The items in the set should be stored in positions
 *  0, 1,... (count-1) of the array
 * The size of the array when the set is first created should be 10. 
 * It keeps the items in order according to the comparator.
 *  Ie, when it adds a new item, it must put it in the right place
 *  When it searches for an item, it should use binary search.
 *  Note, the comparator assumes that the items are Comparable.
 * It does not allow null items or duplicates.
 *  Attempting to add null should throw an exception
 *  Adding an item which is already present should simply return false, without
 *  changing the set.
 * It should always compare items using equals()  (not using ==)
 * When full, it will create a new array of double the current size, and
 *  copy all the items over to the new array
 */

public class SortedArraySet <E> extends AbstractSet <E> {

    // Data fields
    private static int INITIALCAPACITY = 10;
    private E[] data;
    private int count = 0;

    private Comparator<E> comp;    // use comp to compare items. 

    // --- Constructors --------------------------------------
    /** Constructor to make a new empty set */
    @SuppressWarnings("unchecked")  // this will stop Java complaining about the cast
    public SortedArraySet() {
        comp = new ComparableComparator();
        data = (E[]) new Object[INITIALCAPACITY];
    }

    /** Constructor to make a new empty set, with a given comparator */
    @SuppressWarnings("unchecked")  // this will stop Java complaining about the cast
    public SortedArraySet(Comparator<E> comparator) {
        comp = comparator;
        data = (E[]) new Object[INITIALCAPACITY];
    }

    /** Constructor that takes a whole collection and sorts it all at once */
    @SuppressWarnings("unchecked")  // this will stop Java complaining about the cast
    public SortedArraySet(Collection<E> col) {
        comp = new ComparableComparator();
        /*# YOUR CODE HERE */
        count = col.size();
        data = col.toArray((E[]) new Object[0]);
        Arrays.sort(data);
    }

    /** Constructor that takes a whole collection and sorts it all at once */
    @SuppressWarnings("unchecked")  // this will stop Java complaining about the cast
    public SortedArraySet(Collection<E> col, Comparator<E> comparator) {
        comp = comparator;
        /*# YOUR CODE HERE */
        count = col.size();
        data = col.toArray((E[]) new Object[0]);
        Arrays.sort(data, comp);
    }

    // --- Methods --------------------------------------

    /** Returns number of items in collection as integer 
     */
    public int size () {
        return count;
    }

    /** Add the specified item to this set (if it is not a duplicate of an item
     *  already in the set).
     *  Will not add the null value (throws an IllegalArgumentException in this case)
     *  Return true if the collection changes, and false if it did not change.
     */
    public boolean add(E item) {
        /*# YOUR CODE HERE */
        
        if(contains((Object)item)){
            throw new IllegalArgumentException("Cats2");
           // return false; //checks if duplicate
        }
        if(item == null){ //check if null
            throw new IllegalArgumentException("Item is null");
        }
        int oldcount = count;
        count++;
        ensureCapacity(); //calls capacity
        if(oldcount == 0) {
            data[0] = item;
            return true;
        }
        int index = Arrays.binarySearch(data,0,oldcount, item); //returns a negitive number of array position
        
        if(index >= 0){
            throw new IllegalArgumentException("Cats");
            //count--;
            //return false; //dont' add items twice 
        }
        index = -1 - index;
        for(int i = index; i < oldcount; i++){ 
            data[i+1] = data[i];
        }
        data[index] = item;
        //count++;
       
        return true;
    }

    /** Return true if this set contains the specified item. */
    @SuppressWarnings("unchecked")  // stops Java complaining about the call to compare 
    public boolean contains(Object item) {
        if(item == null || item == 0) return false;
        E itm = (E) item;
        return data[findIndex((E) item)] == itm;
    }

    /** Remove an item matching a given item
    *  Return true if the item was present and then removed.
    *  Make no change to the set and return false if the item is not present.
    */
    @SuppressWarnings("unchecked")  // stops Java complaining about the call to compare 
    public boolean remove (Object item) {
        E itm = (E) item;
        /*# YOUR CODE HERE */
        int index =  findIndex(itm);
        if(index == -1){
            return false;
        }
        
        for(int i = index; i < count-1; i++){ 
                data[i] = data[i+1];
        }
        data[count-1] = null;
        count--;
        return true;
        
    }

    // It is much more convenient to define the following method 
    // and use it in the methods above.

    /** Find the index of where an item is in the dataarray,
     *  (or where it ought to be, if it's not there).
     *  Assumes that the item is not null.
     *  Uses binary search and requires that the items are kept in order.
     *  Should use  compareTo to compare values */
    private int findIndex(E item){
        /*# YOUR CODE HERE */
        
        Comparable<E> value = (Comparable<E>)item;
        int low = 0;
        int high = count;
        while(low < high){
            int mid = (low+high)/2;
            if(value.compareTo(data[mid]) > 0){
                low = mid+1;
            }else{
                high = mid;
            }
            
        }
         return low;
    }

    /** Ensure data array has sufficient number of items
    *  to add a new item 
    */
    @SuppressWarnings("unchecked")  // this will stop Java complaining about the cast
    private void ensureCapacity () {
        if (count < data.length) return;
        E[] newArray = (E[]) (new Object[data.length*2]);
        for (int i = 0; i < count; i++)
            newArray[i] = data[i];
        data = newArray;
    }

    // --- Iterator and Comparator --------------------------------------

    /** Return an iterator over the items in this set. */
    public Iterator <E> iterator() {
        return new SortedArraySetIterator(this);
    }

    private class SortedArraySetIterator implements Iterator <E> {
        // needs fields, constructor, hasNext(), next(), and remove()
        private SortedArraySet<E> set;
        private int nextIndex = 0;
        private boolean canRemove = false;

        private SortedArraySetIterator(SortedArraySet<E> s) {
            set = s;
        }

        /** Return true if iterator has at least one more item */
        public boolean hasNext() {
            return (nextIndex < set.count);
        }

        /** Return next item in the set */
        public E next() {
            if (nextIndex >= set.count)
                throw new NoSuchElementException();
            canRemove = true;
            return set.data[nextIndex++];
        }

        /** Remove from the set the last item returned by the iterator.
         *  Can only be called once per call to next.
         */
        public void remove() {
            if (! canRemove)
                throw new IllegalStateException();
            set.remove(set.data[nextIndex-1]);
            canRemove = false;
        }
    }

    /** This is a comparator that assumes that E's are Comparable:
    it casts them to Comparable<E>, and then calls their compare method.
    It will fail if E's are not Comparable - in this case, the set should
    have been constructed with an appropriate comparator.
     */
    private class ComparableComparator implements Comparator<E>{
        @SuppressWarnings("unchecked")  // this will stop Java complaining about the cast
        public int compare(E item, E other){
            Comparable<E> itm = (Comparable<E>) item;
            return itm.compareTo(other);
        }
    }
}

