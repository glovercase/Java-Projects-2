// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.


/* Code for COMP103, Assignment 4
 * Name:Casey Glover
 * Usercode:
 * ID: 300280613
 */

import java.util.Iterator;
import java.util.Queue;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

/** ArrayQueueTest
 *  A JUnit class for testing an ArrayQueue
 */

public class ArrayQueueTest {
    
    private Queue<String> queue;

    /*# YOUR CODE HERE */
    @Before
    public void intialiseEmptyQueue(){
        queue = new ArrayQueue<String>();
    }
    
    /** method to initialise the queue with n values **/
    public void fillQueue(int n){
        for(int i = 1; i <= n; i++){
            queue.offer("p" + i);
        }
    }
    //--------------------------------------------------------
    
    @Test
    public void testIsEmptyQueue(){
        assertTrue("A new queue should be empty", queue.isEmpty());
    }
    
    @Test
    public void testEmptyQueueHasSizeZero(){
        assertEquals("An empty queue should have size zero",0 , queue.size());
    }
    
    @Test
    public void testEmptyQueueDoesContainNull(){
        assertEquals("An empty queue should not contain null",null, queue.peek());
    }
    
    @Test
    public void testOfferToQueue(){
        for(int i = 1; i <= 20; i++){
            assertTrue("Queue should successfully add item " + i, queue.offer("p"+ i));
            assertFalse("Queue should not be empty after offer ", queue.isEmpty());
            assertEquals("Size should be " + i + " after " + i + " offers", i, queue.size());  
             
        }
        
    }
    
    @Test 
    public void testAddingNull(){
        queue.offer(null);
    }
    
   @Test
   public void testPollOfQueue(){
       fillQueue(20);
       assertEquals("p1 should be polled successfully.", "p1", queue.poll());     
    }
    
    @Test
    public void testEmptyTheQueue(){
        fillQueue(16);   
        for(int i = 1; i <=16; i++){
            String pos = "p"+i;
            assertEquals(pos+" should be polled from the queue.",pos, queue.poll());     
            
        }
        assertTrue("Queue should be empty after polling the queue.", queue.isEmpty());
        
    }
    
    @Test
    public void testHasNextOnEmptyIterator(){
        Iterator<String> iterator = queue.iterator();
        assertFalse("An empty queue does not have next item.", iterator.hasNext());
        
    }
    
    
    @Test (expected = java.util.NoSuchElementException.class)
    public void testNextOnEmptyIterator(){
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        
    }
    
    @Test
    public void testIterator(){
        fillQueue(20);
        Iterator<String> iterator = queue.iterator();
        int count = 0;
        while(iterator.hasNext()){
            iterator.next();
            count++;
        }
        
        assertEquals("Iterator should have retruned 20 items", 20, count);
    }
    

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("ArrayQueueTest");
    }
    
    

}
