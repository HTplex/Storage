package array;
import java.util.Scanner;
public class ArrayA05lottary {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		boolean[] lottary=new boolean [99];
		int i=input.nextInt();
		int a=99;
		while (i!=0){
			System.out.println("please input the number ("+a+") numbers left");
			lottary[i-1]=true;
			i=input.nextInt();
			a--;
		}
		boolean all=true;
		for(int aa=1;aa<100;aa++){
			if (lottary[aa-1]==false)
				all=false;
		}
		if(all)
			System.out.println("the tickets is covered");
			else System.out.println("the ticket is not covered");
	}
}
