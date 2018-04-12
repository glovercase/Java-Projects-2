
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main
{

      public static void main(String args[]){
        Animal d = new Dog();
        d.run();
        Animal c = new Cat();
        c.run();
        Cat cat = new Cat();
        cat.run();
        Animal h = new Human();
        h.run();
        ((Animal)h).run();
        Human human = new Human();
        human.run();
        //human.super.run();
    }
}
