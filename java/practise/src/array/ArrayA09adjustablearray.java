package array;
import java.util.Scanner;
public class ArrayA09adjustablearray {
	public static void main(String[]args){
		int i=-1;
		System.out.println(max(1,2,34,7,5,6,7,8,9,9,87,65,4,4,3,87,0,45,67));
		System.out.println(max(1,2));
		System.out.println();
		}
	public static int max(int...a){
		int max=0;
		for(int i=0;i<a.length;i++){
			if(a[i]>max)
				max=a[i];
		}
		return max;
	}

}
