package freepritice;
import java.util.Scanner;
public class Dot {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int[] a=new int[7];
		for(int i=0;i<10000;i++){
			double r=4;
			for(int ii=0;ii<6;ii++){
				double d=(1-2*(int)(2*Math.random()))/2.0;
				r+=d;
			}
			a[(int)(r-1)]++;
		}
		for(int i:a){
			System.out.println(i);
		}
	}
}
