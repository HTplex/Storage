package array;
import java.util.Scanner;
public class ArrayB05abbr {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input 10 numbers");
		int[] list=new int [10];
		for (int i=0;i<list.length;i++){
			list [i]=input.nextInt();
		}
		int[] out=new int[10];
		for(int i=0;i<list.length;i++){
			out[list[i]]++;
		}
		for (int i=0;i<list.length;i++){
			if(out[i]!=0)
				System.out.print(i+" ");
		}
	}
}
