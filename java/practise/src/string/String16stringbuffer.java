package string;
import java.util.Scanner;
public class String16stringbuffer {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		StringBuffer s=new StringBuffer("hallo i'am Bob");
		String a=s.toString();
		int i=input.nextInt();
		System.out.println(s);
		i=input.nextInt();
		System.out.println(s.toString());
		i=input.nextInt();
		System.out.println(s.capacity());
		i=input.nextInt();
		System.out.println(s.charAt(3));
		i=input.nextInt();
		System.out.println(s.length());
		i=input.nextInt();
		System.out.println(s.substring(4));
		i=input.nextInt();
		System.out.println(s.substring(4,8));
		s.trimToSize();
		i=input.nextInt();
		System.out.println(s.length()+" "+s.capacity());
		s.setLength(2);
		i=input.nextInt();
		System.out.println(s);
	}
}
