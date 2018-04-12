// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

import java.io.*;
import ecs100.*;

/**  
 * Implements a binary search tree node.
 *   
 *  @author: Thomas Kuehne (based on previous code)
 */

public class BSTNode <E extends Comparable<E>> {

    private E value;
    private BSTNode<E> left;
    public BSTNode<E> right;

    // constructs a node with a value
    public BSTNode(E value) {
        this.value = value;
    }

    // Getters...

    public E getValue() {
        return value;
    }

    public BSTNode<E> getLeft() {
        return left;
    }

    public BSTNode<E> getRight() {
        return right;
    }

    /** 
     * Returns true if the subtree formed by the receiver contains 'item'
     * 
     * CORE
     *
     * ASSUMPTION: 'item' is not 'null'.
     * 
     * HINT: A recursive approach leads to a very short and simple code. 
     * 
     * HINT: use 'compareTo(...)' in order to compare the parameter 
     * with the data in the node. 
     * 
     * HINT: Make sure that you invoke 'compareTo' by always using
     * the same receiver / argument ordering throughout the program, e.g., 
     * always use the item as the receiver of 'compareTo'.
     *
     *@param item - the item to check for
     *@returns true if the subtree contained 'item'
     *
     */
    public boolean contains(E item) {
        /*# YOUR CODE HERE */
        if(value.equals(item)) return true;
        if(item.compareTo(value) < 0){
            if(left != null){
                return left.contains(item);
            }else{
                return true;
            }
        }else if(item.compareTo(value) > 0){
            if(right != null){
                return right.contains(item);
            }else{
                return true;
            }

        }else{

            // no matching node was found
            return false;
        }
    }

    /**
     * Adds an item to the subtree formed by the receiver.
     * 
     * CORE
     * 
     * Must not add an item, if it is already in the tree.
     * 
     * HINT: use 'compareTo(...)' in order to compare the parameter 
     * with the data in the node. 
     * 
     * @param item - the value to be added
     * @returns false, if the item was in the subtree already. Returns true otherwise.
     */
    public boolean add(E item) {
        /*# YOUR CODE HERE */

        if(value.equals(item)) return false;//if value equals item  return false

        if(item.compareTo(value) < 0){ //if value less than item
            if(left != null){
                left.add(item);
            }else{
                left = new BSTNode(item);
                return true;
            }
        }else{ //else belongs on the right
            if(right != null){
                right.add(item);
            }else{
                right = new BSTNode(item);
                return true;
            }

        }
        //if there is no left child 
        //insert child and return true
        //else add value to left child

        //if there is no right child 
        //insert as right child and return true
        //else add value to right child

        // not proper code, just needed to enable compilation
        return false;
    }

    /**
     * Returns the height of the receiver node.
     * 
     * CORE 
     * 
     * HINT: The number of children the receiver node may have, implies
     * four cases to deal with (none, left, right, left & right).
     * 
     * @returns the height of the receiver
     */
    public int height() {
        /*# YOUR CODE HERE */

        if(value == null) return -1;
        int heightLeft = -1;
        int heightRight = -1;

        if(left != null){
            heightLeft = left.height();
        }
        if(right != null){
            heightRight = right.height();
        }
        if(heightLeft > heightRight){
            return heightLeft+1;
        }else{
            return heightRight+1;
        }
        //check left 
        //check right

        //if greater reutn +1

        // just to enable compilation
        //return 0;
    }

    /**
     * Returns the length of the shortest branch in the subtree formed by the receiver.
     * 
     * COMPLETION
     * 
     * @returns the minimum of all branch lenghts starting from the receiver. 
     * 
     */
    public int minDepth() {
        /*# YOUR CODE HERE */

        // just to enable compilation
        return 0;
    }

    /** 
     *  Removes an item in the subtree formed by the receiver.
     *  
     *  COMPLETION
     *  
     *  ASSUMPTION: The item to be removed does exist. 
     *  The case that it cannot be found, should be dealt with before this method is called.
     *  
     *  Performs two tasks:
     *  1. locates the node to be removed, and
     *  2. replaces the node with a suitable node from its subtrees.
     *  
     *  HINT: use 'compareTo(...)' in order to compare the parameter 
     *  with the data in the node. 
     * 
     *  HINT: For task 2, you should use call method 'replacementSubtreeFromChildren'
     *  to obtain this node. 
     *  
     *  HINT: When replacing a node, it is sufficient to change the value of the existing node
     *  with the value of the node that conceptually replaces it. There is no need to actually 
     *  replace the node object as such. 
     *  
     *  @param item - the item to be removed
     *  @returns the reference to the subtree with the item removed.
     *  
     *  HINT: Often the returned reference will be the receiver node, but it is possible that
     *  the receiver itself needs to be removed. If you use a recursive approach, the
     *  latter case is the base case. 
     *   
     */
    public BSTNode<E>remove(E item) {
        /*# YOUR CODE HERE */
        if(value.equals(item)){
            return replacementSubtreeFromChildren(this.left, this.right);
        }
        if(value.compareTo(item) < 0){
            left.remove(item);
        }else{
            right.remove(item);
        }

        // there was no need to replace the receiver node
        return this;  
    }

    /**
     *  Returns a replacement subtree for the receiver node (which is to be removed).
     *  
     *  COMPLETION
     *  
     *  The replacement subtree is determined from the children of the node to be removed.
     *  
     *  HINT: There are several cases:
     *  - node has no children    => return null
     *  - node has only one child => return the child
     *  - node has two children   => return the current subtree but with
     *       a) its (local) root replaced by the leftmost node in the right subtree, and
     *       b) the leftmmost node in the right subtree removed.
     *       
     * @param left - the left subtree from which to include items.       
     * @param right - the right subtree from which to include items.       
     * @returns a reference to a subtree which contains all items from 'left' and 'right' combined.      
     *                                          
     */
    private BSTNode<E> replacementSubtreeFromChildren(BSTNode<E> left, BSTNode<E> right) {
        /*# YOUR CODE HERE */
        if(left == null && right == null) return null;
        //if(

        // not a simple case => return modified node
        return this;
    }

    /**
     *  Returns the leftmost node in the subtree formed by the receiver. 
     *  
     *  COMPLETION
     *  
     *  HINT: The code is very simple. Just keep descending left branches, 
     *  until it is no longer possible. 
     * 
     * @returns a reference to the leftmost node, starting from the receiver.    
     *                                          
     */
    private BSTNode<E> getLeftmostNode() {
        /*# YOUR CODE HERE */
        //recurse 
        return null;
    }

    /**
     * Prints all the nodes in a subtree to a stream.
     * 
     * @param stream - the output stream 
     */
    public void printAllToStream(PrintStream stream) {
        if (left!=null) 
            left.printAllToStream(stream);

        stream.println(value);

        if (right!=null) 
            right.printAllToStream(stream);
    }

    /**
     * Prints all the nodes in a subtree on the text pane.
     * 
     * Can be useful for debugging purposes, but 
     * is most useful on small sample trees. 
     * 
     * Usage: node.printAll("").
     */
    public void printAll(String indent){
        if (right!=null)
            right.printAll(indent+"    ");

        UI.println(indent + value);

        if (left!=null) 
            left.printAll(indent+"    ");
    }
}
