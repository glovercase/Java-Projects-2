import ecs100.*;
/**
 * Write a description of class move here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class move
{
    // instance variables - replace the example below with your own
    

 public   void pushZerosToEnd()
{
    int[] elements = new int[] {1,2,3,4,0,5,6,0,7,8,9};

        int swapCount = 0;
        int lastIndex = 1;

        for(int i = lastIndex+1; i <=elements.length; i++) {  // skip the very last element
            if(elements[i] == 0) {
                elements[i] = elements[lastIndex+swapCount];
                elements[lastIndex-swapCount] = 0;
                swapCount++;
            }
             UI.print(elements[i]);
        }

       


   }
}
