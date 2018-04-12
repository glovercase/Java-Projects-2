// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103 Assignment
 * Name:Casey Glover
 * Usercode:
 * ID:300280613
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ecs100.UI;

public class Order {

    List<Boolean> hasOrder;
    List<FoodType> wantsOrder;

    private static int maxItemType = 3;
    private static int maxOrderLength = 4;

    public Order() {
        hasOrder = new ArrayList<Boolean>();
        wantsOrder = new ArrayList<FoodType>();
        int length = (int)(Math.random() * maxOrderLength) + 1; //sets the size of the order
        //max order length increases every 5 orders
        for(int i = 0; i < length; i++){
            int pick = (int)(Math.random()*maxItemType); 
            if(pick == 0){
                wantsOrder.add(FoodType.FISH); //adds items to the arraylist
            }else if(pick == 1){
                wantsOrder.add(FoodType.CHIPS);
            }else if(pick == 2){
                wantsOrder.add(FoodType.BURGER);
            }else if(pick == 3){
                wantsOrder.add(FoodType.HOTDOG);
            }else if(pick == 4){
                wantsOrder.add(FoodType.SUNDAE);
            }
            hasOrder.add(false); //sets the has order to all be false

        }

    }

    public static void orderLength(){
        if(maxItemType < 10){
            maxOrderLength++;
        }
    }

    public static void levelUp(){
        if(maxItemType < 5){
            maxItemType++; //to add in hot dog and add in sundae after 15 and 30 delivers
        }
    }

    /** The order is ready as long as there every item that is
     *  wanted is also ready.
     */
    public boolean isReady() {
        for(int i = 0; i < hasOrder.size(); i++){
            if (hasOrder.get(i) == false) return false; 
        }
        return true; // is only ready if all the hasordeers are equal to true.
    }

    /** If the item is wanted but not already in the order,
     *  then put it in the order and return true, to say it was successful.
     *  If the item not wanted, or is already in the order,
     *  then return false to say it failed.
     */
    public boolean addItemToOrder(FoodType item){
        for(int i = 0; i < wantsOrder.size(); i++){
            if(wantsOrder.get(i) == item){
                if(hasOrder.get(i) == false){
                    hasOrder.set(i, true); //checks the multiple orders 
                    return true; 
                }
            }
        }

        return false;
    }

    /** Computes and returns the price of an order.
     *  Core: Uses constants: 2.50 for fish, 1.50 for chips, 5.00 for burger
     *  to add up the prices of each item
     *  Completion: Uses a map of prices to look up prices
     */
    public double getPrice() {
        double price = 0;
        if(!hasOrder.isEmpty()){
            for(FoodType type : wantsOrder ){
                price += FoodType.getPrice(type); //gets the price from foodtype
            }
        }
        return price;
    }

    public void draw(int y) {
        if(!wantsOrder.isEmpty()){
            int x = 10;
            for(int i = 0; i <  wantsOrder.size(); i++){
                if(hasOrder.get(i) == false){
                    UI.drawImage(FoodType.getGreyImage(wantsOrder.get(i)), x, y); //greyscale image
                }else{
                    UI.drawImage(FoodType.getColorImage(wantsOrder.get(i)), x, y); //colored image
                }
                x += 30;

            }

        }

    }
}
