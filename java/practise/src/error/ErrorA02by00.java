package error;
import java.util.Scanner;
public class ErrorA02by00 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int a=input.nextInt();
		int b=input.nextInt();
		try{
			System.out.println(divideplus(a,b));
		}
		catch(ArithmeticException ex){
			System.out.println("you can not divide something by 0");
		}
	}
	public static int divideplus(int a, int b){
		if(b==0)
			throw new ArithmeticException("can not /0");
		return(a/b);
	}
}
