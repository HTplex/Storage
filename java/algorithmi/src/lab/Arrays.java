package lab;
import java.util.Scanner;
public class Arrays {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int i=input.nextInt();
		for(int i1=0;i1<i;i1++){
			System.out.println(i1);
		}
		int[] a=new int[10];
		for(i=0;i<a.length;i++){
			a[i]=i;
		}
		for(i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		input.close();
	}
}
