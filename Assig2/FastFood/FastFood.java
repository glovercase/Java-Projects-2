// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103 Assignment
 * Name: Casey Glover
 * Usercode:glovercase
 * ID: 300280613
 */

import ecs100.*;
import java.util.*;


/** The FastFood game involves customers who generate orders, and the player
 *  who has to fulfill the orders by assembling the right collection of food items.
 *  The goal of the game is to make as much money as possible before
 *  the player gets too far behind the customers and is forced to give up.
 *
 *  The game presents the player with a a queue of orders in a fast food outlet.
 *  The player has to fullfill the customer orders by adding the correct items to
 *  the order at the head of the queue.  
 *  When the order is ready, the player can deliver the order, which will
 *  take it off the queue, and will add the price of the order to the balance.
 *  Whenever the player adds an item to the order that doesn't belong in the
 *  order, the price of the item is subtracted from the balance.
 *  The player can practice by generating orders using the Practice button.
 *  Once the game is started, the orders are generated automatically.
 */
public class FastFood implements UIButtonListener, UIKeyListener{

    private Queue<Order> orders;
    private double balance;
    public static Map<String,Double> prices;

    private int completedOrders = 0;

    public FastFood() {
        orders = new ArrayDeque<Order>();

        UI.addButton("Practice Order", this);
        UI.addButton("Add Fish", this);
        UI.addButton("Add Chips", this);
        UI.addButton("Add Burger", this);
 
        UI.addButton("Add HotDog", this); //Challenge level 2
        UI.addButton("Add Sundae", this); //Challenge level 3
        UI.addButton("Deliver Order", this);
        UI.addButton("Start Game", this);

        UI.setKeyListener(this);

        drawOrders();
        this.run();

    }


    /** Respond to the buttons */
    public void buttonPerformed(String name) {
        if ("Practice Order".equals(name))  {generateOrder();}
        else if ("Add Fish".equals(name))   {addItem("Fish");}
        else if ("Add Chips".equals(name))  {addItem("Chips");}
        else if ("Add Burger".equals(name)) {addItem("Burger");}
        else if ("Add Sundae".equals(name)) {addItem("Sundae");} 
        else if ("Add HotDog".equals(name)) {addItem("HotDog");}
        else if ("Deliver Order".equals(name)) {deliverOrder();}
        else if ("Start Game".equals(name)) {startGame();
        }
        drawOrders();
    }

    public void keyPerformed(String key){
        if(key.equals("f")){ //press the first letter of the item to play space to deliveer
            addItem("Fish");
        }else if(key.equals("c")){
            addItem("Chips");
        }else if(key.equals("b")){
            addItem("Burger");
        }else if(key.equals("h")){
            addItem("HotDog");
        }else if(key.equals("s")){
            addItem("Sundae");
        }else if(key.equals("Space")){
            deliverOrder();
        }
    }

    /** Create a new order and put it on the queue to be processed */
    public void generateOrder() {
        /*# YOUR CODE HERE */
        Order order = new Order(); //makes a new order
        orders.add(order); //add order to the queue
    }

    /** As long as there is an order in the queue, adds the specified
     *  item to the order at the head of the queue,
     *  If adding the item fails (ie, it isn't one of the items
     *  that are wanted by the order) then the price
     *  of the item is deducted from the current balance.
     */
    public void addItem(String item) {
        /*# YOUR CODE HERE */
        Order order = orders.peek(); //cheeks to see if there is 
        FoodType type = FoodType.get(item); //look up the foodtype 
        if(order != null && order.addItemToOrder(type)) return; //add the order

        balance -= FoodType.getPrice(type); // call the price 
        UI.println("Oh no!, You add an unwanted "+item);
        UI.println("$"+FoodType.getPrice(type)+" deducted from your pay :-(");
        UI.println("( Balance: $-"+FoodType.getPrice(type)+" )");

        //if it isn't that item then the price of the item is deducted from the currecnt balance
    }

    /** As long as there is an order at the front of the queue and it is ready,
     *  take the first order off the queue, compute the price of the order,
     *  and update the total balance by adding the order price.
     *  If there is not a ready order on the queue, it prints a warning message
     */
    public void deliverOrder() {
        /*# YOUR CODE HERE */
        Order order = orders.peek(); //checks the front of the queue
        if(order.isReady()){//check if there is an order at the front of the queue and if it is ready
            orders.poll();//take the order off
            double price = order.getPrice();
            balance += price;
            UI.println("That will be $"+price+", please.");
            UI.println("Thanks! Enjoy your meal.");
            UI.println("( Balance: $"+balance+" )");
            completedOrders++; //counts the completed orders
            if(completedOrders == 5 || completedOrders == 10){
                    Order.orderLength();
                }
            if(completedOrders > 15){ // challenge to make the game harder 
                
                Order.levelUp();
                completedOrders = 0;
            }
            
        }else{
            UI.println("Can't deliver order: it's not ready!"); //if there is not a ready order on the queue print a warning message

        }
        //compute the order
        //update the total balance by adding the order price
        

    }

    /** Draw the queue of orders on the Graphics pane.
     *  Also draws the current balance in the top left corner
     */
    public void drawOrders() {
        UI.clearGraphics();
        /*# YOUR CODE HERE */
        int y = 40;
        UI.drawString("$"+balance, 10,10);
        for(Order order: orders){
            order.draw(y);
            y += 40;

        }

    }
    // In the game version, the orders must be automatically generated.
    // The methods below will reset the queue and the current balance,
    // and will then set the gameRunning field to true. This will make
    // the run method start generating orders.
    // The run method is called from the main method, and therefore is in the main
    // thread, which executes concurrently with all the GUI buttons.
    // run  does nothing until the gameRunning field is set to be true
    // Once the gameRunning field is true, then it will generate orders automatically,
    // every timeBetweenOrders milliseconds. It will also makde the games speed up
    // gradually, by steadily reducing the timeBetweenOrders.
    // You do not need to write these methods code.

    private boolean gameRunning = false;
    private long timeBetweenOrders = 5000;

    private void startGame(){
        UI.clearGraphics();
        UI.clearText();
        orders.clear();
        balance = 0;
        timeBetweenOrders = 5000;
        gameRunning = true;
        //	 nextOrder = 0; nextSpeedup = 0;// I don't think they are needed
    }

    public void run() {
        long timeBetweenSpeedups = 2000;
        long timeNextOrder = 0;
        long timeNextSpeedup = 0;
        while (true) {
            UI.sleep(100); // Wait at least 100 milliseconds between actions.
            long now = System.currentTimeMillis();
            if (!gameRunning) continue;  // if gameRunning is false, then don't generate orders
            if (now >= timeNextOrder) {
                timeNextOrder = now + timeBetweenOrders;
                generateOrder();
                drawOrders();
            }
            if (now >= timeNextSpeedup) {   // get faster steadily.
                if (timeBetweenOrders> 200) timeBetweenOrders -= 100; 
                timeNextSpeedup = now + timeBetweenSpeedups;
            }
            if (orders.size() > 20) {
                UI.println("Oh no! You have too many orders waiting! Game over...");
                orders.clear();
                gameRunning = false;
                break;
            }
        }
    }

    public static void main(String args[]) {
        FastFood ff = new FastFood();
    }
}

