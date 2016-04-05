package array;
import java.util.Scanner;
public class ArrayA07mathodarray {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the length");
		int a=input.nextInt();
System.out.println("please input the array you want to mutiply");
			int [] arr=new int [a];
					for (int i=0;i<arr.length;i++){
						System.out.println("input No."+(i+1));
						arr[i]=input.nextInt();
					}
		System.out.println("please input the number you want to copy");
			int i=input.nextInt();
		System.out.println("the mutiple is");
		for(int b:mut(i,arr))
		System.out.print(b+" ");
	}
	public static int[] mut(int a,int[] b){
		for (int i=0;i<b.length;i++){
			b[i]*=a;
		}
		return b;
	}
}
