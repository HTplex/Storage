package loop;
import java.util.Scanner;
public class LoopB35sum {
	public static void main(String[]args){
		double sum=0;
		for(int i=1;i<625;i++){
			sum+=(1/(Math.pow(i, 0.5)+Math.pow(i+1, 0.5)));
		}
		System.out.println((int)(sum*10000)/10000.0);
	}
}
