package mathod;
import java.util.Scanner;
public class MathodB13progression {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number you want");
		int a=input.nextInt();
		System.out.println("i\t\tn");
		for (int i=1;i<=a;i++){
			System.out.println(i+"\t\t"+((int)(pro(i)*10000)/(10000.0)));
		}
	}
	public static double pro(int a){
		double out=0;
		for (int i=1;i<=a;i++){
			out+=i/(i+1.0);
		}
		return out;
	}
}
