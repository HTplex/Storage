package array;
import java.util.Scanner;
public class ArrayB18bubblesort {
	public static void main(String[]args){
		int temp;
		int [] ran=new int [100];
		for(int i=0;i<ran.length;i++){
			ran[i]=(int)(10000*Math.random());
		}
		for(int i=0;i<ran.length;i++){
			System.out.printf("%5d",ran[i]);
			if(i%10==9)
				System.out.println();
		}
		System.out.println("\n\n");
		for(int i=ran.length-2;i>=0;i--){
			for(int ii=i;ii<ran.length-1;ii++){
				if(ran[ii]>ran[ii+1]){
					temp=ran[ii];
					ran[ii]=ran[ii+1];
					ran[ii+1]=temp;
				}//if
			}//for
		}//for
		for(int i=0;i<ran.length;i++){
			System.out.printf("%5d",ran[i]);
			if(i%10==9)
				System.out.println();
		}
	}
}
