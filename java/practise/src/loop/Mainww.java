package loop;
import java.util.Scanner;
public class Mainww {
	public static void main(String[]args){
		int output=2;
		int div=2;
		int []ii=new int[1061];
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
				ii[c]=output-1;
				c++;
			}

		}///for
		for(int w:ii){
			String s=new String();
			
			s=s.valueOf(w);
			StringBuilder ss=new StringBuilder(s);
			ss.reverse();
			String s2=ss.toString();
			int e;
			e=Integer.parseInt(s2);
			if(java.util.Arrays.binarySearch(ii,e)>=0&&e>w){
			System.out.print(w+" ");
			}
		}
	}
}
