package algorithm;
import java.util.Scanner;
public class AacBubbleSort {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("number of the array :");
		int n=input.nextInt();
		int[] a=new int[n];
		for(int i=0;i<n;i++)
			a[i]=input.nextInt();
		int temp;
		for(int i=0;i<n;i++){
			for(int ii=i;ii<n;ii++){
				if(a[ii]<a[i]){
				temp=a[ii];
				a[ii]=a[i];
				a[i]=temp;
				}
			}
		}
		for(int q:a)
			System.out.println(q);
	}
}
