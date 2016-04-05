package loop;
import java.util.Scanner;
public class LoopAgdowhile {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int a=0;
		int sum=0;
	do {
		System.out.print("please input the number you want to add (the answer will display when you input 0)");
		a=input.nextInt();
		sum+=a;
	}
	while (a!=0);
	System.out.println("the final answer is "+sum);
	}
}