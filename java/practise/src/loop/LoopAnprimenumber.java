package loop;
import java.util.Scanner;
public class LoopAnprimenumber {
	public static void main(String[]args){
		int output=2;
		int div=2;
		int []ii=new int[200];
		int c=0;
		for(int i=1;i<=1400;i++){
			while (div<=(int)(Math.pow(output, 0.5))){
				if(output%div!=0){
			div++;}//if
				else{
				output++;
				div=2;
				}//else
			}//while

			output++;
			div=2;
			if(output>1000&&output<10000){
				ii[c]=output;
				c++;
			}

		}///for
	}
}
