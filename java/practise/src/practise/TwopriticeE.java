package practise;
import java.util.Scanner;
public class TwopriticeE {
	public static void main(String[]args){
		System.out.print("Enter the subtotal and a gratuity rate ");
		Scanner input =new Scanner(System.in);
	double subtotal=input.nextDouble();
	double gratuityRate=input.nextDouble();
	double gratuity=((int)(subtotal*gratuityRate))/100.0;
	double total=(int)(100*(gratuity+subtotal))/100.0;
	System.out.println("The gratuity is "+gratuity+"and total is "+total);
	}
}
