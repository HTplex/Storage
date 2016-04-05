package error;
import java.util.Scanner;
public class ErrorA04exmess {
	public static void main(String[]args){
		try{
			System.out.println(k(new int[5]));
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			System.out.println(ex.toString());
			StackTraceElement[] a=ex.getStackTrace();
			for(int i=0;i<a.length;i++){
				System.out.println(a[i].getMethodName());
				System.out.println(a[i].getClassName());
				System.out.println(a[i].getFileName());
				System.out.println(a[i].getLineNumber());
			}
		}
	}
	public static int k(int[] a){
		return a[100];
	}
}
