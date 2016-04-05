package practise;
import java.util.Scanner;

public class Twostamplethree {
	public static void main (String[]args) {
		
		Scanner input =new Scanner(System.in);
		
		System.out.print("Please input 3 numbers and divide them with blank:");
		double num1=input.nextDouble();	
		double num2=input.nextDouble();
		double num3=input.nextDouble();
		double average=(num1+num2+num3)/3;
		System.out.println("The average of num1,num2,num3 is"+average);
	}
}
