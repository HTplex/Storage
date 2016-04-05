package mathod;
import java.util.Scanner;
public class MathodA01sum {
	public static int sum(int i1,int i2){
		int sum=0;
		for (int i=i1;i<=i2;i++)
			sum+=i;
			return sum;
		
	}
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print ("please input the begin number");
		int b=input.nextInt();
		System.out.print("please input the end number");
		int e=input.nextInt();
		System.out.print(sum(b,e));
	}
}
