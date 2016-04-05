package practise;
import java.util.Scanner;
public class TwopraitceA {
	public static void main(String[]args){
		System.out.print("Enter a degree in Celsius");
		Scanner input=new Scanner(System.in);
		double x=input.nextDouble();
		double y=((5/9.0)*x)+32;
		System.out.println(x+"Celsius is "+y+" Fahrenheit");
	}
}
