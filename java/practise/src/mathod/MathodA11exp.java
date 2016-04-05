package mathod;
import java.util.Scanner;
public class MathodA11exp {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number you want to test");
		int a=input.nextInt();
		System.out.println(Math.exp(a));
		System.out.println(Math.log(a));
		System.out.println(Math.log10(a));
		System.out.println(Math.pow(a,a));
		System.out.println(Math.sqrt(a));
		
	}
}
