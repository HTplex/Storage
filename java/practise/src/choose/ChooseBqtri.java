package choose;
import java.util.Scanner;
public class ChooseBqtri {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input the three edge :");
		double a=input.nextDouble();
		double b=input.nextDouble();
		double c=input.nextDouble();
		System.out.print("can edges "+a+" "+b+" and "+c+" form a triangle?"+(a+b>c&&a+c>b&&b+c>a));
	}
}
