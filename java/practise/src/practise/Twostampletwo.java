package practise;
import java.util.Scanner;

public class Twostampletwo {
	public static void main (String[]args) {
	Scanner input = new Scanner(System.in);
	System.out.print("Enter a number for radius:");
	double radius=input.nextDouble();
	double area=radius*radius*3.1415926;
	System.out.println("The area of the circle of radius"+radius+"is"+area);
		
	}
}
