import java.io.*;
/**
 * Write a description of class HeapLinkedNode here.
 * 
 * @author Casey Glover
 * 
 */
public class HeapLinkedNode <E extends Comparable<? super E> > 
{
    // instance variables - replace the example below with your own
    private E element;
    private HeapLinkedNode<E> parent;
    private HeapLinkedNode<E> right;
    private HeapLinkedNode<E> left;

    /**
     * Constructor for objects of class HeapLinkedNode
     */
    public HeapLinkedNode(E aElement)
    {
        // initialise instance variables
        element = aElement;
    }
    
    //Getters
    
    public E getValue(){
        return element;
    }
    
    public HeapLinkedNode<E> getParent(){
        return parent;
    }
    
     public void setParent(HeapLinkedNode newParent){
        parent = newParent;
    }
    
    public HeapLinkedNode<E> getLeft(){
        return left;
    }
    
    public HeapLinkedNode<E> getRight(){
        return right;
    }

   
}
