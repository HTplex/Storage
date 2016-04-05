package string;
import java.util.Scanner;
public class StringA04method2 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("input first string");
		String s1=input.next();
		System.out.println("input second string");
		String s2=input.next();
		System.out.println("number of character: ");
		System.out.println("first: "+s1.length());
		System.out.println("second: "+s2.length()+"\n");
		System.out.println("third character: ");
		System.out.println("first: "+s1.charAt(2));
		System.out.println("second: "+s2.charAt(3)+"\n");
		System.out.println("stick together:"+(s1+s2)+"\n"+s1.concat(s2));
	}
}
