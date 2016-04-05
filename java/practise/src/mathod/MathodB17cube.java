package mathod;
import java.util.Scanner;
public class MathodB17cube {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	System.out.println("please input the number you want");
	int a=input.nextInt();
	sq(a);
	}
	public static void sq(int a){
		for (int i=1;i<=a;i++){
			for(int ii=1;ii<=a;ii++){
			System.out.print((int)(2*Math.random()));
		}
			System.out.println();
	}
	}
}
