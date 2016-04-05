package loop;
import java.util.Scanner;
public class LoopAlbrecon {
	public static void main(String[]args){
		int a=1;
		int sum=0;
		while(a<100){
			sum+=a;
			a++;
			if (sum%5==0)
				break;
		}
			System.out.println("the answer is "+sum);
	}
}
