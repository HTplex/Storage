package practise;
import java.util.Scanner;
public class TwopraticeB {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("Enter the radius and length of a cylinder");
		double radius=input.nextDouble();
		double length=input.nextDouble();
		final double PI=3.1415926;
		double area=radius*radius*PI;
		double volume=area*length;
		System.out.println("The area is "+area);
		System.out.println("The volume ia"+volume);
	}
}
