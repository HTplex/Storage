package mathod;
import java.util.Scanner;
public class MathodA04void {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input your grade");
		double i=input.nextDouble();
		showgrade(i);
	}
	public static void showgrade(double score){
		if (score==100) System.out.println("S");
		else if(score>=90) System.out.println("A");
		else if(score>=80) System.out.println("B");
		else if(score>=70) System.out.println("C");
		else if(score>=60) System.out.println("D");
		else System.out.println("F");
	}
}
