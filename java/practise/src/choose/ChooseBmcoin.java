package choose;
import java.util.Scanner;
public class ChooseBmcoin {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	int a=(int)(2*Math.random());
	System.out.print("please input the side you guess");
	int b=input.nextInt(); 
	System.out.println("your guess is "+(a==b));
	}
}
