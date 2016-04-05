package choose;
import java.util.Scanner;
public class ChooseBaX2 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("we are solving the f of ax^2+bx+c=0, please input a b c");
		double a=input.nextDouble();
		double b=input.nextDouble();
		double c=input.nextDouble();
		if (b*b-4*a*c>0){
		double xa=((-b+Math.pow(b*b-4*a*c,0.5))/2*a);
		double xb=((-b-Math.pow(b*b-4*a*c,0.5))/2*a);
		System.out.println("the first answer is "+xa+"\nthe second answer is "+xb);}
		else System.out.println("there is no answer for the equation");
	}
}
