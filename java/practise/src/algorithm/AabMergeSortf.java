package algorithm;
import java.util.Scanner;
public class AabMergeSortf {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("number of the array :");
		int n=input.nextInt();
		int[] a=new int[n];
		for(int i=0;i<n;i++)
			a[i]=input.nextInt();
		mergesort(a,0,a.length);
		for(int q:a)
			System.out.print(q+" ");
	}
	public static int [] mergecell(int[] a,int p,int q,int r){
		int n1=q-p+1;
		int n2=r-q;
		int [] a1=new int[n1+1];
		int [] a2=new int[n2+1];
		for(int i=0;i<n1;i++)
			a1[i]=a[p+i];
		for(int i=0;i<n2;i++)
			a2[i]=a[q+i-1];
		a1[n1]=a1[n1-1]+1;
		a2[n2]=a2[n2-1]+1;
		int i=0;
		int j=0;
		for(int ii=p;ii<r;ii++){
			if(a1[i]>=a2[j]){
				a[ii]=a2[j];
				j++;
			}
			else{
				a[ii]=a1[i];
				i++;
			}
		}
		return a;
	}
	public static int [] mergesort(int[] a,int p,int r){
		if(p<r-1){
			int q=((p+r)/2);
			mergesort(a,p,q);
			mergesort(a,q+1,r);
			mergecell(a,p,q,r);
		}
		return a;
	}
}
