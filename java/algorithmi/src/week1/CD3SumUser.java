package week1;
import java.util.Scanner;
public class CD3SumUser {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		CD3Sum a=new CD3Sum(n);
		a.facinp();
		long t=System.currentTimeMillis();
		
		//a.trisumdull();
		a.trisumimp();
		
		
		System.out.println((System.currentTimeMillis()-t));
		input.close();
	}
}
