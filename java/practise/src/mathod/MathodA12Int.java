package mathod;
import java.util.Scanner;
public class MathodA12Int {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the nuber you want to change to integer");
		double a=input.nextDouble();
		System.out.println(Math.ceil(a));//add
		System.out.println(Math.floor(a));//minus
		System.out.println(Math.rint(a));//nearest
		
	}
}
