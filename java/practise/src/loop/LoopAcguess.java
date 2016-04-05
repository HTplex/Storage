package loop;
import java.util.Scanner;
public class LoopAcguess {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
	int com=(int)(10000*Math.random());
	System.out.println("please input a number between 0-9999");
	int count=0;
	int user=input.nextInt();
	while (user!=com){
		if (user>com){
			System.out.println("your answer is to high, guess again");
			user=input.nextInt();
			count++;
		}
			else {
				System.out.println("your answer is to low, guess again");
			user=input.nextInt();
			count++;
			}
		}
	System.out.println("CONGRATULATIONS! you are right, you had guessed "+count+" times");
	}
}
