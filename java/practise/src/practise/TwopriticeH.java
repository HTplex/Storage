package practise;
import java.util.Scanner;
public class TwopriticeH {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("Enter a ASCLL code: ");
		int ascii=input.nextInt();
		char a=(char)ascii;
		System.out.println("The character for ASCII code "+ascii+" is "+a);
	}
}
