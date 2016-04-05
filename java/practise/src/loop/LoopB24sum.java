package loop;
import java.util.Scanner;
public class LoopB24sum {
	public static void main(String[]args){
		double sum=0.0;
		for (int i=1;i<98;i+=2)
			sum+=i/(i+2.0);
		System.out.print(sum);
		
	}
}
