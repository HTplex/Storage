package error;
import java.util.Scanner;
public class ExceptionR02 {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	int i1=input.nextInt();
	int i2=input.nextInt();
	try{
		if(i2==0)
			throw new ArithmeticException("shit!");
		System.out.println(i1/i2);
	}
	catch(ArithmeticException ex){
		System.out.println("shit!");
	}
	System.out.println("look at me, it still running!");
	}
}
