package array;
import java.util.Scanner;
public class ArrayA10search {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int [] a=new int [10000];
		for (int i=0;i<a.length;i++)
			a[i]=i+1;
		int temp,r=0;
		for (int i=0;i<a.length;i++){
			r=(int)(Math.random()*a.length);
			temp=a[i];
			a[i]=a[r];
			a[r]=temp;
		}
		int line=0;
		for(int i:a){
			System.out.print("a["+line+"]="+i+"\t");
		line++;
		if (line%50==0)
			System.out.println();
		}
		long start=System.nanoTime();
		System.out.println("please input the number you want to find");
		int i=input.nextInt();
		System.out.println("the number is No."+find(i,a));
		long end=System.nanoTime();
		System.out.println((end-start)/1000000000.0);
	}
	public static int find(int a,int[] b){
		int ii=0;
		for (int i=0;i<b.length;i++){
			if(b[i]==a)
			ii=i;
		}
		return ii;
	}
}
