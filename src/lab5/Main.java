package lab5;
import java.util.Scanner;
public class Main {
    public static void Main(String[] args)
    {
        Cat firstCat = new Cat("Tom", "white",16);
        System.out.println("Cat name"+firstCat.getName());
        System.out.println("Cat colour"+firstCat.getColour());
        System.out.println("Cat age"+ firstCat.getAge());

        firstCat.play();
        firstCat.feed();
        firstCat.sleep();
    }
}
