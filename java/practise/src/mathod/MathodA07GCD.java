package mathod;
import java.util.Scanner;
public class MathodA07GCD {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the first number");
		int a=input.nextInt();
		System.out.println("please input the Second number");
		int b=input.nextInt();
		System.out.println("the gcd is "+gcd(a,b));
	}
	public static int gcd(int a,int b){
		int d=0;
		for (int i=1;i<a&&i<b;i++){
			if(a%i==0&&b%i==0){
				d=i;
			}
		}		
			return d;
	}
}
