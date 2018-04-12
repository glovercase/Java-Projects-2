/*# i used an enum because i thought it was the best solution because there was a finite number of types of items
   in the game you do not add new types of items so I thought using an enum to define the types at compile time was the cleanest way to go
   */

import java.util.*;  

public enum FoodType{

    FISH(2.30, "Fish"), CHIPS(1.50, "Chips"), BURGER(5.00, "Burger"), HOTDOG(3.00, "HotDog"), SUNDAE(2.00, "Sundae");
    
    private static Map<String, FoodType> names;
    private static boolean initialised = false;
    
    private double price; 
    private String foodItem;
    
    private FoodType(double price, String foodItem){
        this.price = price;
        this.foodItem = foodItem;
    }
    
    public static FoodType get(String name){
        
        if(!initialised){  // makes the one map 
            names = new HashMap<String, FoodType>();
            names.put("Fish", FISH);
            names.put("Chips", CHIPS);
            names.put("Burger", BURGER);
            names.put("HotDog", HOTDOG);
            names.put("Sundae", SUNDAE);
            initialised = true;
        }
        
        return names.get(name);
    }
    
    public static double getPrice(FoodType type){
        return type.price; // to call the price
    }
    
    public static String getGreyImage(FoodType type){
        return type.foodItem+"-grey.png"; //to call the grey scale image
    }
    
    public static String getColorImage(FoodType type){
        return type.foodItem+".png"; //to call the image
    }
    
}



