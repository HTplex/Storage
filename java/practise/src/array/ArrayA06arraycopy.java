package array;
import java.util.Scanner;
public class ArrayA06arraycopy {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int [] inpu=new int [10];
		int [] output =new int [20];
		for(int i=0;i<inpu.length;i++){
			System.out.println("input NO."+(i+1));
			inpu[i]=input.nextInt();
		}
		System.arraycopy(inpu,0,output,3,9);
		for(int i:output){
			System.out.print(i);
		}
	}
}
