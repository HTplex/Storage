package practise;
import java.util.Scanner;

public class Twostampleseven {
	public static void main (String[]args){
		
		Scanner input=new Scanner(System.in);
		System.out.print("Please enter the degree in Fahrnheit");
		
		double Fahrnheit=input.nextDouble();
		double celsius=(5.0/9)*(Fahrnheit-32);
		System.out.println("the degree is"+celsius+"in celsius");
	}
}