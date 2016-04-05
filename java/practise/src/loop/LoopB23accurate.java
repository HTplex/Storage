package loop;
import java.util.Scanner;
public class LoopB23accurate {
	public static void main(String[]args){
		double sum1=0,sum2=0;
		for (int i=1;i<100000;i++){
			sum1+=1.0/i;
			
		}
		for (int i=99999;i>=1;i--){
		sum2+=1.0/i;
		}
		System.out.println(sum1+"   "+sum2);
	}
}
