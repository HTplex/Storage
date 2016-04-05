package error;
import java.util.Scanner;
public class ErrorA06finally {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		try{
			int a=input.nextInt();
			if (a==0)
				throw new ArithmeticException("no 0");
		}
		catch(ArithmeticException e){
			System.out.println("whatever");
		}
		finally{
			System.out.println("it will always be here");
		}
		System.out.println("the end");
	}
}
