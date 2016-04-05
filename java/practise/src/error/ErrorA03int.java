package error;
import java.util.*;
public class ErrorA03int {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("input an integer");
	try{
		int i=input.nextInt();
		System.out.println(i*i);
	}
	catch(InputMismatchException ex){
		System.out.println("you got wax in your eyes? the System said input an integer!");
	}
	}
	
}
