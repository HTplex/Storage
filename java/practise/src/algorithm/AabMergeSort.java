package algorithm;
import java.util.Scanner;
public class AabMergeSort {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("number of the array :");
		int n=input.nextInt();
		int[] a=new int[n];
		for(int i=0;i<n;i++)
			a[i]=input.nextInt();
		mergesort(a,0,a.length-1);
		for(int q:a)
			System.out.print(q+" ");
	}
	public static int[] con(int[] a,int s,int m,int e){
		int[]a1=new int [m-s+2];
		int[]a2=new int [e-m+1];
		for(int i=0;i<a1.length-1;i++)
			a1[i]=a[s+i];
		for(int i=0;i<a2.length-1;i++)
			a2[i]=a[m+1+i];
		a1[m-s+1]=Integer.MAX_VALUE;
		a2[e-m]=Integer.MAX_VALUE;
		int c1=0;
		int c2=0;
		for(int i=s;i<=e;i++){
			if(a1[c1]<=a2[c2]){
				a[i]=a1[c1];
				c1++;
			}
			else {
				a[i]=a2[c2];
			c2++;
			}
		}
		return a;
	}
	public static int[] mergesort(int[] a,int s,int e){		
			if(s<e){
			int m=(int)((s+e)/2);
			mergesort(a,s,m);
			mergesort(a,m+1,e);
			con(a,s,m,e);
		}
		return a;
	}
}