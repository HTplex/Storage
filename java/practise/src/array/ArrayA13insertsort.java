package array;
import java.util.Scanner;
public class ArrayA13insertsort {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number of the array");
		int number=input.nextInt();
		int [] list=new int [number];
		for (int i=0;i<number;i++){
		System.out.print("a["+i+"]=  ");
		list[i]=input.nextInt();
		}
		for (int iii=0;iii<list.length;iii++){
			list[iii]=insertsort(list)[iii];
		}
		for(int m:list)
		System.out.println(m);
	}
	public static int[] insertsort(int[] a){
		int min;
		int temp;
		for(int i=0;i<a.length;i++){
			min=0;
			while(a[i]>a[min])
				min++;
			temp=a[i];
			for(int c=i;c>min;c--){
				a[c]=a[c-1];
			}
			a[min]=temp;
		}//for
		return a;
	}
	
}
