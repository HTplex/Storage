package mathod;
import java.util.Scanner;
public class MathodB01pentanumber {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
		System.out.println("please input a number you want");
		int x=input.nextInt();	
	System.out.println("the petnumber is "+getpn(x));
	}
	public static int getpn(int x){
		int p=x*(3*x-1)/2;
		return p;
	}
}
