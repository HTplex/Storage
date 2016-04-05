package practise;
import java.util.Scanner;
public class TwopriticeF {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	System.out.print("Enter between 0 and 1000");
	int x=input.nextInt();
	int kilo=(int)(x/1000);
	int kiloremain=x%1000;
	int hund=(int)(kiloremain/100);
	int hundremain=x%100;
	int deca=(int)(hundremain/10);
	int mono=(int)(hundremain%10);
	int fina=kilo+hund+deca+mono;
	System.out.println("The sum of the digits is "+fina);
	}
}