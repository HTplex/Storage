package loop;
import java.util.Scanner;
public class LoopB20prime2 {
	public static void main(String[]args){
		int output=2;
		int div=2;
		int[] i3=new int[400];
		int c=0;
		for(int i=1000;i<10000;i++){
			while (div<=(int)(Math.pow(output, 0.5))){
				if(output%div!=0){
			div++;}//if
				else{
				output++;
				div=2;
				}//else
			}//while
			if (output>1000)
				break;
			else if(i%8==0){
			System.out.println();
			output++;
			div=2;
			}//ifdisplay
			else{
				i3[c]=output;
				c++;
				output++;
				div=2;
			}
			
		}///for
		for(int i=0;i<100;i++)System.out.println(i3[i]);
	}
}
