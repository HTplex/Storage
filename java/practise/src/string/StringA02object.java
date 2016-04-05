package string;
import java.util.Scanner;
public class StringA02object {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String a="Welcome to string";
		String b=new String("Welcome to string");
		String c=new String("Welcome to string");
		String d="Welcome to string";
	
	System.out.println("a=b: "+(a==b));
	System.out.println("a=d: "+(a==d));
	System.out.println("b=c:"+(b==c));
	}
}
