package mathod;
import java.util.Scanner;
public class MathodB02sumdig {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number you want to change to x");
		long a=input.nextLong();
		System.out.println("the number is "+sumdig(a));
	}
	public static long sumdig(long a){
		int sum=0;
		for(long i=1000000000;i>0;i/=10){
			sum+=(int)(a/i);
			if ((int)(a/i)!=0){
				a-=i*((int)(a/i));
			}
		}
		return sum;
	}
}
