package error;
import java.util.Scanner;
public class ErrorA07exexex {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int x=input.nextInt();
		try{
			mathod1();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
	public static void mathod1()throws Exception{
		try{
			mathod2();
		}
		catch(Exception ex){
			throw new Exception("aha two exceptions",ex);
		}
		
	}
	public static void mathod2()throws Exception{
		throw new Exception("i have been in mahtod2");
	}
}
