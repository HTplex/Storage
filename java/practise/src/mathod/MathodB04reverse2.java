package mathod;
import java.util.Scanner;
public class MathodB04reverse2 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number you want");
		int a=input.nextInt();
		System.out.println("the reverse number is "+reverse(a));
	}
	public static long reverse (long a){
		long reverse=0;
		long i,c;
		for (i=1000000000;i>=a;i/=10){
			}
		long k=i;
		while(k>0){
			c=(int)(a/k);
			reverse+=c*(i/k);
			a%=k;
			k/=10;
		}
		return reverse;
	}
}
