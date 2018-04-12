// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 5
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;
import java.awt.Color;

/** Uses recursion to
- draw a path of white stepping stones across a pond.
- draw trees around the pond
 */
public class Pond implements UIButtonListener, UIMouseListener {

    // Fields
    private double lastX;
    private double lastY;
    private static final double BRANCH_ANGLE = 0.5;
    //private double length = 50;

    public Pond() {
        UI.addButton("Clear", this);
        UI.addButton("Stepping Stones", this);
        UI.setMouseListener(this);
        drawPond();
        UI.printMessage("Click mouse to draw trees");
    }

    /** Draw a path of stepping stones, starting at (x,y)
     *	with the first stone of the given width, and each successive
     *	stone 80% of the previous stone.
     *	The stones look approximately right if the height of the oval
     *	is 1/4 the width.
     */
    public void steppingStones(double x, double y, double width) {
        /*# YOUR CODE HERE */
        if(x < 50 || y < 250 || width < 5){
            return; 
        }
        UI.setColor(Color.white);
        UI.fillOval(x, y, width, width*1/4);
        x = x + width*1.5/4;
        y = y - width*1.5/4;

        steppingStones(x,y,width*0.8);
    }

    /** Draw a tree with the base at (xBot, yBot).
     *  The top of the first branch should be at xTop, yTop.
     *  Then draw three smaller trees on the top of this branch
     *   with tops above, to the left, and to the right of this branch.
     */
    public void drawTree(double xBot, double yBot, double height) {
        /*# YOUR CODE HERE */
       
        // height = height/2;  
       
        double width = Math.sin(BRANCH_ANGLE) * height;
        double length = Math.cos(BRANCH_ANGLE) * height;
            
        
        //length = length/2;
        //width = width/2;  

       //xBot = xTop;
       // yBot -= height;
        UI.drawLine(xBot, yBot, xBot- width, yBot-length);
        
        UI.drawLine(xBot, yBot, xBot, yBot-height);
        
        UI.drawLine(xBot, yBot, xBot + width, yBot-length);
        if(height < 5){
            return;
        }
       height /= 2;
       drawTree(xBot-width, yBot-length, height);  
        drawTree(xBot, yBot-height, height);
        drawTree(xBot+width, yBot-length,  height);

    }

    public void drawPond(){
        UI.clearGraphics();
        UI.setColor(Color.blue);
        UI.fillOval(50, 250, 400, 150);
    }

    /** Respond to button presses */
    public void buttonPerformed(String button) {
        if ( button.equals("Clear")) {
            drawPond();

        }
        else if (button.equals("Stepping Stones")) {
            UI.setColor(Color.white);
            steppingStones(100, 350, 40);
        }
    }

    /** Respond to mouse events */
    public void mousePerformed(String action, double x, double y) {
        if (action.equals("released")) {
            UI.setColor(Color.green.darker().darker());
            UI.drawLine(x,y, x, y-50);
            drawTree(x, y-50, 50);
            
        }
    }

    // Main
    public static void main(String[] arguments){
        new Pond();
    }	

}
