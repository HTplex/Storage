package mathod;
import java.util.Scanner;
public class MathodA03testA2max {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("Please input one number");
		int a=input.nextInt();
		System.out.print("please input another number");
		int b=input.nextInt();
		System.out.println(MathodA02max.max(a, b));
	}
	}