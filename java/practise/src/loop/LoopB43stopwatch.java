package loop;
import java.util.Scanner;
public class LoopB43stopwatch {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("Welcome to super stopwatch\npress anynumber and enter to start"
				+ "\npress 0 and enter again to end");
		int start=input.nextInt();
		long a=System.nanoTime();
			start=input.nextInt();
			long b=System.nanoTime();
			System.out.print("total time is "+(double)(b-a)/1000000000+"seconds");
		
	}
}
