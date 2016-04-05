package error;
import java.util.Scanner;
public class ErrorA01by0 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int a=input.nextInt();
		int b=input.nextInt();
		try{
		//	if(b==0)
			//	throw new ArithmeticException("Divisor can not be 0");
			 
			System.out.println("the answer is "+a/b);
		}
		catch (ArithmeticException ex){
			System.out.println("Exception: an integer can not be divided by 0");
		}
		System.out.println("can you see? the program is still continued");
	}
}
