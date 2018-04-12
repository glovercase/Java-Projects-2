// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

import java.util.*;
/**
 * Implements a priority queue based on a heap that is
 * represented with an array.
 */
public class HeapArrayQueue <E extends Comparable<? super E> > extends AbstractQueue <E> { 

    @SuppressWarnings("unchecked") 
    protected E[] data = (E[])(new Comparable[7]);
    private int count = 0;
    private static final int FRONT = 0; //front of the queue

    public int size() {
        return count;
    }

    public boolean isEmpty() { 
        return size() == 0; 
    }

    /**
     * Returns the element with the top priority in the queue. 
     * 
     * HINT: This is like 'poll()' without the removal of the element. 
     * 
     * @returns the next element if present, or 'null' if the queue is empty.
     */
    public E peek() {
        /*# YOUR CODE HERE */
        if(count == 0){ 
            return  null;
        }else{
            return data[FRONT]; //retun the front of the array
        }
    }

    /**
     * Removes the element with the top priority from the queue and returns it.
     * 
     * HINT: The 'data' array should contain a heap so the element with the top priority
     * sits at index '0'. After its removal, you need to restore the heap property again,
     * using 'sinkDownFromIndex(...)'.
     * 
     * @returns the next element in the queue, or 'null' if the queue is empty.
     */
    public E poll() {
        /*# YOUR CODE HERE */
        if(count == 0){
            return null;
        }
        E root = data[FRONT]; //temp from the front of the queue
        data[FRONT] = data[--count]; //the last item is set to the front 
        sinkDownFromIndex(FRONT); //reorder
        return root; //return 

    }

    /**
     * Enqueues an element.
     * 
     * If the element to be added is 'null', it is not added. 
     * 
     * HINT: Make use of 'ensureCapacity' to make sure that the array can 
     * accommodate one more element. 
     * 
     * @param element - the element to be added to the queue
     * 
     * @returns true, if the element could be added
     */
    public boolean offer(E element) {
        /*# YOUR CODE HERE */
        if(element == null) return false;
        ensureCapacity(); //check if array is large enough
        data[count] = element; //back of the queue
        bubbleUpFromIndex(count); //reorder
        count += 1; //add 1 to the count
        return true;

    }
    
    private boolean compare(int indexOne, int indexTwo)
    {
        return data[indexOne].compareTo(data[indexTwo]) < 0; //compare which is larger
    }

    private void sinkDownFromIndex(int nodeIndex) {
        int leftChild = 2* nodeIndex+1; //left equation
        int rightChild = leftChild +1; // right equation
        int largestChild; 
        if(leftChild >= count) return; //to finsh the recursion
        if(compare(leftChild, rightChild)){ //compare the kids
            largestChild = rightChild; //set largest
        }else{
            largestChild = leftChild;
        }
        if (compare(largestChild, nodeIndex)) {
            swap(largestChild, nodeIndex);//if true swap 
            sinkDownFromIndex(largestChild);//recurse
        }
      
    }

    private void bubbleUpFromIndex(int nodeIndex) {
        /*# YOUR CODE HERE */
        if(nodeIndex == 0) return; //to finish the recursion 
        int parent = (nodeIndex-1)/2; //parent equation 

        if (compare(nodeIndex, parent)) {
            swap(parent, nodeIndex); //if true swap
            bubbleUpFromIndex(parent);//recurse
        }

    }
    /**
     * Swaps two elements in the supporting array.
     */
    private void swap(int from, int to) {
        E temp = data[from];
        data[from] = data[to];
        data[to] = temp;
    }

    /**
     *  Increases the size of the supporting array, if necessary
     */
    private void ensureCapacity() {
        if (count == data.length) {
            @SuppressWarnings("unchecked") 
            E[] newData = (E[])new Comparable[data.length * 2];

            // copy data elements
            for (int loop = 0; loop < count; loop++) {
                newData[loop] = data[loop];
            }
            data = newData;
        }
        return;
    }

    // no iterator implementation required for this assignment
    public Iterator<E> iterator() { 
        return null; 
    }
}
