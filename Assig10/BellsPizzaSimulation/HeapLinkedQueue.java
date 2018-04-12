import java.util.*;
public class HeapLinkedQueue  <E extends Comparable<? super E> > extends AbstractQueue <E>
{
    // instance variables - replace the example below with your own
    @SuppressWarnings("unchecked") 
    //the type stored at the node
    private E element;
    private int count = 0;
    
    
    
    private HeapLinkedNode<E> root;
    
    public HeapLinkedQueue(E aElement)
    {
        element = aElement;
    }
   
    
    public int size(){
        return count;
    }
    
    public boolean isEmpty(){
        return count == 0;
    }
        
    
    
    public E peek(){
        if(count == 0){
            return null;
        }else{
            return root.getValue();
        }
    }
    
    public E poll(){
        if(count == 0){
            return null;
        }
        E front = root.getValue();
        HeapLinkedNode temp = root;
        while(temp != null){
            
        }
        //remove front;
        //
        sinkDownFromIndex(temp);
        return front;
        
    }
    
    public boolean offer(E element){
        if(element == null) return false;
        HeapLinkedNode newNode = new HeapLinkedNode(element);
        //add node to a child
        bubbleUpFromIndex(newNode);
        count += 1;
        return true;
    }
    
    private boolean compare(HeapLinkedNode node1, HeapLinkedNode node2){
        return node1.getValue().compareTo(node2.getValue()) > 0;
    }
    
    private void swap(HeapLinkedNode node1, HeapLinkedNode node2){
        HeapLinkedNode parent = node1.getParent();
        
    }
    
    private void sinkDownFromIndex(HeapLinkedNode node){
        HeapLinkedNode largestChild;
        if(node == null) return;
        if(compare(node.getLeft(), node.getRight())){
            largestChild = node.getRight();
        }else{
            largestChild = node.getLeft();
        }
        if(compare(largestChild, node)){
            swap(largestChild, node);
            sinkDownFromIndex(largestChild);
        }
    }
    
    private void bubbleUpFromIndex(HeapLinkedNode node){
        if(node == null) return;
        HeapLinkedNode parent = node.getParent();
        if(compare(node, parent)){
            swap(parent, node);
            bubbleUpFromIndex(parent);
        }
        
        
    }
    
   
    
    public Iterator<E> iterator(){
        return null;
    }
    
   
}
