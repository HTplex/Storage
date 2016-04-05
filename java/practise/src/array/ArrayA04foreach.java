package array;
import java.util.Scanner;
public class ArrayA04foreach {
public static void main(String[]args){
	Scanner input=new Scanner(System.in);
		int []easy=new int [10];
		for (int i=0;i<easy.length;i++){
			System.out.println("input the number");
			easy[i]=input.nextInt();
		}
		for (int i:easy)
			System.out.print(i);
}
}
