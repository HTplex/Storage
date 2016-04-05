package week1;
import java.util.Scanner;
public class BBQuickFind {
	public static void main(String[]args){
		
		Scanner input=new Scanner(System.in);
		int i=input.nextInt();
		int[] a=new int[i];
		for(int i1=0;i1<i;i1++){
			a[i1]=i1;
		}

		
		union(a,0,2);
		union(a,2,4);
		union(a,3,5);
		union(a,5,2);
		union(a,2,3);
		System.out.println(connected(a,0,5));
		System.out.println(connected(a,2,8));
		input.close();
	}
	
	
	public static void union(int[] ar,int a,int b){
		if(connected(ar,a,b)){
			System.out.println(a+" & "+b+"is already connected");
		}
		else for(int i=0;i<ar.length;i++){
			if(connected(ar,b,i)){
				ar[i]=ar[a];
			}
		}
	}
	public static Boolean connected(int[] ar, int a, int b){
		return ar[a]==ar[b];
	}
}
