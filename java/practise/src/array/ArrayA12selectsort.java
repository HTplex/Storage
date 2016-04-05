package array;
import java.util.Scanner;
public class ArrayA12selectsort {
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
			list[iii]=selectsort(list)[iii];
		}
		for(int m:list)
		System.out.println(m);
	}
	public static int[] selectsort(int[] a){
		for(int i=0;i<a.length;i++){
		int currmin=a[i];
		int curr=i;
		for(int ii=i;ii<a.length;ii++){
			if(currmin>a[ii]){
				currmin=a[ii];
				curr=ii;
			}//if
		}
		a[curr]=a[i];
		a[i]=currmin;
		}
		return a;
	}
}
