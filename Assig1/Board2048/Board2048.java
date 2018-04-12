//Challenge COMP 103 Assignment 1
import ecs100.*;
import java.awt.Color;
import java.util.*;
/**
 * 
 * @author Casey Glover
 * ID: 300280613
 */
public class Board2048 
{
    // instance variables - replace the example below with your own
    private final int LIMIT = 7; // to determine the ration of twos over fours (the closer to 10 the more twos)
    private final int TARGET = 2048;
    private int SCORE;
    private final int COLS;
    private final int ROWS;
    private int [][] array; 

    private final int boardLeft = 80;
    private final int boardTop = 40;
    private final int tileSize = 50;

    /**
     * Constructor for objects of class Board2048
     */
    public Board2048(int size)
    {
        SCORE = 0;
        COLS = size;
        ROWS = size;
        array = new int[ROWS][COLS];
    }

    public boolean hasReachedTarget() {    
        if(SCORE > TARGET){
            return true;
        }
        return false;
    }
    
    public void updateScore(){
        for(int i = 0; i < array.length; i++){
            for( int j = 0; j < array[0].length; j++){
                SCORE += array[i][j];
            }
        }
        
    }
    
    public int getScore(){
        return SCORE;
    }

    public boolean isGameOver() {
        return isArrayFull() & !isMovePossible();
    }

    public boolean isArrayFull(){
        for(int i = 0; i < array.length; i++){
            for( int j = 0; j < array[0].length; j++){
                if(array[i][j] == 0) return false;
            }
        }
        return true;
    }

    public boolean isMovePossible(){
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length-1; j++){
                int k = j+1;
                if(array[i][j] == array[i][k] || array[i][j] == array[k][j]) return true;
            } 
        }
        return false;
    }

    private int numEmptyTiles() {
        /*# YOUR CODE HERE */
        int count = 0;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[0].length; j++){
                if(array[i][j] == 0) count++;
            }
        }
        return count;
    }

    public void insertRandomTile() {
        /*# YOUR CODE HERE */
        double ran = Math.floor(Math.random() *3);
        int random = (int)ran;

        if(isMovePossible()){
            if(array[random][random] == 0){
                if(Math.random() * 10 < LIMIT){
                    array[random][random] = 2;
                }else{
                    array[random][random] = 4;
                }

            } 
        }
    }

    public void left() {
        for(int r = 0; r < array.length; r++){
            for(int i = 0; i < array[0].length-1; i++){
                int nextValid = -1;

                for(int j = i+1; j < array[0].length; j++){
                    if(array[r][j] != 0){
                        nextValid = j;
                        break;
                    }
                }

                if(nextValid == -1) break;
                if(array[r][i] == 0) {
                    array[r][i] = array[r][nextValid];
                    array[r][nextValid] = 0;
                }
                else if(array[r][i] == array[r][nextValid]){ //Cheek to see if they are matching
                    array[r][i] = array[r][i] + array[r][nextValid];
                    array[r][nextValid] = 0;
                }    
            }
        }

    }
    public void up(){
        for(int r = 0; r < array.length; r++){
            for(int i = 0; i < array[0].length-1; i++){
                int nextValid = -1;

                for(int j = i+1; j < array[0].length; j++){
                    if(array[j][r] != 0){
                        nextValid = j;
                        break;
                    }
                }

                if(nextValid == -1) break;
                if(array[i][r] == 0) {
                    array[i][r] = array[nextValid][r];
                    array[nextValid][r] = 0;
                }
                else if(array[i][r] == array[nextValid][r]){ //Cheek to see if they are matching
                    array[i][r] = array[i][r] + array[nextValid][r];
                    array[nextValid][r] = 0;
                }    
            }
        }

    }


    public void right() {
        for(int r = array.length-1; r>=0; r--){
            for(int i = array[0].length-1; i>0; i--){
                int nextValid = -1;
                for(int j = i-1; j >= 0; j--){
                    if(array[r][j] != 0){
                        nextValid = j;
                        break;
                    }
                }

                if(nextValid == -1) break;
                if(array[r][i] == 0){
                    array[r][i] = array[r][i] + array[r][nextValid];
                    array[r][nextValid] = 0;
                }
                else if(array[r][i] == array[r][nextValid]){
                    array[r][i] = array[r][i] + array[r][nextValid];
                    array[r][nextValid] = 0;
                } 
            }
        }

    }

    
    public void down(){
        for(int r = array.length-1; r>=0; r--){
            for(int i = array[0].length-1; i>0; i--){
                int nextValid = -1;
                for(int j = i-1; j >= 0; j--){
                    if(array[j][r] != 0){
                        nextValid = j;
                        break;
                    }
                }

                if(nextValid == -1) break;
                if(array[i][r] == 0){
                    array[i][r] = array[i][r] + array[nextValid][r];
                    array[nextValid][r] = 0;
                }
                else if(array[i][r] == array[nextValid][r]){
                    array[i][r] = array[i][r] + array[nextValid][r];
                    array[nextValid][r] = 0;
                } 
            }
        }

    }

    public void redraw(){
        UI.clearGraphics();
        for (int row = 0; row < array.length; row++){
            for(int col = 0; col < array[0].length; col++){
                drawTile(row, col);
            }
        }
        UI.repaintGraphics();
    }

    private void drawTile(int row, int col){
        double left = boardLeft+col*tileSize;
        double top = boardTop+row*tileSize;

        UI.setColor(getColor(array[row][col]));
        UI.fillRect(left,top,tileSize,tileSize);

        UI.setColor(Color.black);
        UI.drawRect(left,top,tileSize,tileSize);

        UI.setFontSize(20);
        if(array[row][col] == 0) return;
        double x = left + tileSize * 0.3;
        double y = top + tileSize * 0.6;
        UI.drawString(""+array[row][col], x,y);

    }

    private Color getColor(int value) {
        switch (value) {
            case 0 : { return Color.white; }     
            case 2 : { return Color.gray; }    
            case 4 : { return Color.orange; }  
            case 8 : { return Color.red; }   
            case 16 : { return Color.cyan; }     
            case 32 : { return Color.blue; }
            case 64 : { return Color.green; }
            case 128 : { return Color.yellow; }
            case 256 : { return Color.yellow; }
            case 512 : { return Color.pink; }
            default: {return Color.black;}
        }
    }

}
