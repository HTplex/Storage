package loop;
import java.util.Scanner;
public class LoopB07tuitionfee {
	public static void main(String[]args){
		int fee=10000;
		int sum=0;
		for (int i=1;i<=10;i++){
			fee*=1.05;
			if (i<=4)
				sum+=fee;
				else continue;
			
		}
		System.out.println("the total fee for first 4 years is"+sum+"\nthe tuition 10 years later is "+fee);
	}
}
