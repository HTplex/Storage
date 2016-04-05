package array;
import java.util.Scanner;
public class ArrayA11searchplus {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int [] a=new int [10000];
		for (int i=0;i<a.length;i++)
			a[i]=i+1;
		int line=0;
		for(int m:a){
			System.out.print("a["+m+"]="+m+"\t");
			line++;
			if (line%100==0)
				System.out.println();
		}
		long start=System.nanoTime();
		System.out.println("please input the number you want to find");
		int i=input.nextInt();
		System.out.println("the number is"+find(i+1,a));
		long end=System.nanoTime();
		System.out.println((end-start)/1000000000.0);
	}
	public static int find(int a,int[] b){
		int small=0;
		int big=b.length;
		int mid=(int)((small+big)/2);
		while(a!=b[mid]){
			mid=(int)((small+big)/2);
			if(a<b[mid]){
				big=mid;
			}
			else if(a>b[mid]){
				small=mid;
			}
		}
		return mid;
	}
}