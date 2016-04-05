package algorithm;
import java.util.Scanner;
public class AaaInsertionSort {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
			System.out.print("number of the array :");
			int n=input.nextInt();
			int[] a=new int[n];
			for(int i=0;i<n;i++)
				a[i]=input.nextInt();
			//input
			int key;
			int i1;
			for(int i=1;i<a.length;i++){
				key=a[i];
				i1=0;
				while(a[i1]<key)
					i1++;
				for(int i2=i-1;i2>=i1;i2--)
					a[i2+1]=a[i2];
				a[i1]=key;
			}
			for(int q:a)
				System.out.print(q+" ");
	}
}
