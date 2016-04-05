package array;
import java.util.Scanner;
public class ArrayB07countran {
	public static void main(String[]args){
		int [] ran=new int [100];
		int [] count=new int [10];
		for(int i=0;i<ran.length;i++)
			ran[i]=(int)(10*Math.random());
		for(int i=0;i<ran.length;i++){
			count[ran[i]]++;
		}
		for (int i=0;i<100;i++){
			System.out.print(ran[i]+" ");
			if (i%10==9)
				System.out.println();
			}
		for(int i=0;i<10;i++)
			System.out.println(i+": "+count[i]);
	}
	
}
