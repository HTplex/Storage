package practise;
import java.util.Scanner;

public class Twostampleeleven {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	System.out.print("please insert x");
	double x=input.nextDouble();
	double y=(int)(x*100)/100.0;
	System.out.println("transformed x ="+y);
	} 
}
