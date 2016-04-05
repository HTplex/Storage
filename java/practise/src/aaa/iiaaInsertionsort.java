package aaa;
import java.util.Scanner;
public class iiaaInsertionsort {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		int[] e=new int[n];
		int cache=0;
		for(int i=0;i<n;i++){
			e[i]=input.nextInt();
		}
		
		for(int i=1;i<n;i++){
			for(int i2=i;i2>0;i2--){
				if(e[i2]<e[i2-1]){
					cache=e[i2];
					e[i2]=e[i2-1];
					e[i2-1]=cache;
				}
			}
		}
		
		for(int i=0;i<e.length;i++){
			System.out.println(e[i]);
		}
	}
}
