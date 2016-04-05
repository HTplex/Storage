package array;
import java.util.Scanner;
public class ArrayA07void {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("input1");
		int i=input.nextInt();
		System.out.println("input2");
		int i2=input.nextInt();
		System.out.println("i="+i+"\ni2="+i2);
		swap(i,i2);
		System.out.println("then i="+i+"\ni2="+i2);
		int[] lala=new int[5];
		for (int ii=0;ii<lala.length;ii++){
			System.out.println("input NO."+(ii+1));
			lala[ii]=input.nextInt();
		}
		swapp(lala);
		for (int ii:lala)
		System.out.print(ii);
	}
	public static void swap(int a,int b){
		int temp;
		temp=a;
		a=b;
		b=temp;
	}
	public static void swapp(int[] a){
		int temp;
		temp=a[1];
		a[1]=a[2];
		a[2]=temp;
	}
}
