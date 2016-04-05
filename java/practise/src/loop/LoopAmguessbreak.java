package loop;
import java.util.Scanner;
public class LoopAmguessbreak {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int guess=(int)(10000*Math.random());
		System.out.println("Welcome to the exciting guessing game ");
		while(true){
			System.out.print("please enter your guess");
			int user=input.nextInt();
			if (guess==user)
				break;
			else if (guess>user)
				System.out.println("your answer is too low");
			else if (guess<user)
				System.out.println("your answer is too high");
		}
		System.out.println("your answer is right!");
	}
}
