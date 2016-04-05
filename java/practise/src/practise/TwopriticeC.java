package practise;
import java.util.Scanner;
public class TwopriticeC {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("Enter a vaule in feet");
		double x=input.nextDouble();
		double y=(0.305)*x;
		System.out.println(x+"feet is "+y+"meters");
		
	}
	
}
