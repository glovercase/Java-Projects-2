
import java.util.*;
import java.lang.reflect.Array;
import ecs100.*;

/**
 * ArraySet - a Set collection;
 *
 * The implementation uses an array and a count to store the items.
 *  The items in the set should be stored in positions
 *  0, 1,... (count-1) of the array
 * The size of the array when the set is first created should be 10. 
 * It does not keep the items in any particular order, and may change the
 *  order of the remaining items when removing items.  Eg, it can always add
 *  a new item at the end, and it can move the last item into the place of an
 *  item being deleted - there is no need to shift all  the items up or down
 *  to keep them in order.
 * It does not allow null items or duplicates.
 *  Attempting to add null should throw an exception
 *  Adding an item which is already present should simply return false, without
 *  changing the set.
 * It should always compare items using equals()  (not using ==)
 * When full, it will create a new array of double the current size, and
 *  copy all the items over to the new array
 */

public class ArraySet <E> extends AbstractSet <E> {

    // Data fields
    /*# YOUR CODE HERE */
    private int maxArraySize = 10;
    private E[] a = (E[]) new Object[maxArraySize];

    
    private int count = 0; 
    // Constructors
    @SuppressWarnings("unchecked")  // this will stop Java complaining
    public ArraySet() {
        /*# YOUR CODE HERE */
        E[] a = (E[]) new Object[maxArraySize];   
    }

    // Methods

    /** Returns number of items in collection as integer 
     */
    public int size () {
        /*# YOUR CODE HERE */
        return count;
    }

    /** Add the specified item to this set (if it is not a duplicate of an item
     *  already in the set).
     *  Will not add the null value (throws an IllegalArgumentException in this case)
     *  Return true if the collection changes, and false if it did not change.
     */
    public boolean add(E item) {
        /*# YOUR CODE HERE */
        if(contains((Object)item)) return false;
        if(item == null) {
            throw new IllegalArgumentException("hi");
        }
        ensureCapacity();
        a[count] = item;
        count++;
        return true;
    }

    /** Return true if this set contains the specified item. */
    public boolean contains(Object item) {
        /*# YOUR CODE HERE */
        if(item == null) return false;
        for(int i = 0; i < count; i++){
            if(item.equals((Object)a[i])) return true;
        }
        return false;
    }

    /** Remove an item matching a given item
     *  Return true if the item was present and then removed.
     *  Make no change to the set and return false if the item is not present.
     */
    public boolean remove (Object item) {
        /*# YOUR CODE HERE */
        for(int i = 0; i < count; i++){
            if(item.equals(a[i])){
                a[i] = a[count-1];
                a[count-1] = null;
                count --;
                return true;
            }
        }
        return false;
    }

    /** Return an iterator over the items in this set. */
    public Iterator <E> iterator() {
        /*# YOUR CODE HERE */
        return new ArraySetIterator<E>();
    }

    /** Ensure data array has sufficient number of items
    *  to add a new item 
    */
    @SuppressWarnings("unchecked")  // this will stop Java complaining
    private void ensureCapacity () {
        /*# YOUR CODE HERE */
        if(count >= maxArraySize){
            E[] temp = (E[]) new Object[maxArraySize*2];
            maxArraySize = maxArraySize*2;
            for(int i = 0; i < count; i++){
                temp[i] = a[i];
            }
            a = temp;
        }
    }

    // You may find it convenient to define the following method and use it in
    // the methods above, but you don't need to do it this way.

    /** Find the index of an item in the dataarray, or -1 if not present
     *  Assumes that the item is not null 
     */
    private int findIndex(Object item) {
        /*# YOUR CODE HERE */
        for(int i = 0; i < count; i++){
            if(item.equals(a[i])){
               return i; 
            }
        }
        return -1;
    }

    private class ArraySetIterator <E> implements Iterator <E> {

        // needs fields, constructor, hasNext(), next(), and remove()
        //private final int ARRAY_SIZE = 10;
        private E[] a;
        private int nextIndex;
        private boolean canRemove = false;

        /*# YOUR CODE HERE */
        @SuppressWarnings("unchecked")
        public ArraySetIterator(){
            a = (E[]) new Object[maxArraySize];
        }

        /** Return true if iterator has at least one more item */
        public boolean hasNext() {
            /*# YOUR CODE HERE */
            return (nextIndex < count);
        }

        /** Return next item in the set */
        public E next() {
            /*# YOUR CODE HERE */
            if(nextIndex >= count) throw new NoSuchElementException();
            canRemove = true;
            E temp = a[nextIndex];
            nextIndex++;
            return temp; //temp;
        }

        /** Remove from the set the last item returned by the iterator.
         *  Can only be called once per call to next.
         */
        public void remove() {
            /*# YOUR CODE HERE */
            if(!canRemove) throw new IllegalStateException();
            canRemove = false;
            nextIndex--;
           a[nextIndex] = null;
            //equal to null shift everything one update count
        }
    }
}
