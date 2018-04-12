import ecs100.*;
import java.awt.Color;

/**
 * 
 * Assignment 1 Spiral 
 * @author Casey Glover 
 * @ID 300280613
 */
public class Spiral implements UIButtonListener
{
    // instance variables - replace the example below with your own
    private double sqSize = 50;
    private int[] line = new int[]{1,2,3,4,5,6,7,8,9,10};
    private int[][] grid = new int[10][10];
    private int[] spiral = new int[100];
    private int row;
    private int count = 1;
    private final int cellSize = 50;

    /**
     * Constructor for objects of class Spiral
     */
    public Spiral()
    {
        UI.addButton("Line", this);
        UI.addButton("Square", this);
        UI.addButton("Spiral", this);
        UI.addButton("Clear", this);
    }

    /**

     */
    public void buttonPerformed(String button)
    {
        if(button.equals("Line")){UI.clearGraphics();this.line();}
        else if (button.equals("Square")){UI.clearGraphics();this.square();}
        else if (button.equals("Spiral")){UI.clearGraphics();this.spiral();}
        else if (button.equals("Clear")){UI.clearGraphics();}
    }

    public void line(){
        int gridLeft = 100;
        int gridTop = 50;
        for(int i =0; i<line.length; i++){
            UI.setColor(new Color(25*line[i]));
            UI.fillRect(gridTop, gridLeft, cellSize, cellSize);
            String num = Integer.toString(line[i]);
            UI.setColor(Color.white);
            UI.drawString(num, gridTop+20, gridLeft+20, false);
            gridTop += cellSize;
        }
    }

    public void square(){
        int gridLeft = 50;
        int gridTop = 50;
        int count = 1; 
        for(int row = 0; row < grid.length; row++){
            gridTop = 100;
            for(int col = 0; col < grid[0].length; col++){
                grid[row][col] = count;
                UI.setColor(new Color(2*grid[row][col]));
                UI.fillRect(gridTop, gridLeft, cellSize, cellSize);
                String num = Integer.toString(grid[row][col]);
                UI.setColor(Color.white);
                UI.drawString(num, gridTop+20, gridLeft+20, false);
                gridTop += cellSize;
                count ++;
            }
            gridLeft += cellSize;
        }

    }

    public void spiral(){
        int n = 9;
        int gridLeft = 50;
        int gridTop = 50;

        while(n > 0 ){ //
            for(row = 0; row < n; row++){ //move right
                spiral[row] = count; //add to the array
                UI.setColor(new Color(2*spiral[row])); //change the color
                UI.fillRect(gridLeft, gridTop, cellSize, cellSize); //fill rect
                UI.setColor(Color.white); //white text
                String num = Integer.toString(spiral[row]); //change to string
                UI.drawString(num, gridLeft+20, gridTop+20, false);
                gridLeft += cellSize; //increment
                count ++; //increment
            }
            for(row = 0; row < n; row++){ //move down
                spiral[row] = count;
                UI.setColor(new Color(2*spiral[row]));
                UI.fillRect(gridLeft, gridTop, cellSize, cellSize);
                UI.setColor(Color.white);
                String num = Integer.toString(spiral[row]);
                UI.drawString(num, gridLeft+20, gridTop+20, false);
                gridTop += cellSize;
                count++;
            }

            for(row = 0; row <n; row ++){     //move left        
                spiral[row] = count;
                UI.setColor(new Color(2*spiral[row]));
                UI.fillRect(gridLeft, gridTop, cellSize, cellSize);
                UI.setColor(Color.white);                
                String num = Integer.toString(spiral[row]);
                UI.drawString(num, gridLeft+20, gridTop+20, false);
                gridLeft -= cellSize;
                count++;
            }

            for(row = 0; row <n; row ++){ //move up
                spiral[row] = count;
                UI.setColor(new Color(2*spiral[row]));
                UI.fillRect(gridLeft, gridTop, cellSize, cellSize);
                UI.setColor(Color.white);
                String num = Integer.toString(spiral[row]);
                UI.drawString(num, gridLeft+20, gridTop+20, false);
                gridTop -= cellSize;
                count++;
            }
            gridLeft += cellSize;
            gridTop += cellSize;
            n -= 2; 
        }
        count = 1;

    }

}
