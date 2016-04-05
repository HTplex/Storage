package mathod;
import java.util.Scanner;
public class MathodA06voidsteady2 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input number a");
		int a=input.nextInt();
		System.out.println("please input number b");
		int b=input.nextInt();
		System.out.println("(beforevoid)\tnow x is"+a+"   now y is "+b);
		swap(a,b);
		System.out.println("(aftervoid)\tnow x is"+a+"   now y is "+b);
	}
	public static void swap (int a,int b){
		System.out.println("(beforeswap)\tnow x is"+a+"   now y is "+b);
		int c;
		c=a;
		a=b;
		b=c;
		System.out.println("(afterswap)\tnow x is"+a+"   now y is "+b);
	}
}
