package loop;
import java.util.Scanner;
public class LoopAhfor {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input the integer you want");
		long sum=0;
		int a=input.nextInt();
		for (int i=1 ;i<=a;i++){//can invariable more than one value but can only be used in for()
			sum+=i;
		}
		System.out.println("the sum from 1 to "+a+" is "+sum);
	}
}
