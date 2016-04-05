package string;
import java.util.Scanner;
public class StringA08index {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=new String("hello here i am again, the test string in string, what a fun to be a long string~");
		int i=input.nextInt();
		System.out.println(s);
		i=input.nextInt();
		System.out.println(s.indexOf('a'));
		i=input.nextInt();
		System.out.println(s.indexOf("string"));
		i=input.nextInt();
		System.out.println(s.lastIndexOf('a'));
		i=input.nextInt();
		System.out.println(s.lastIndexOf("string"));
		i=input.nextInt();
		System.out.println(s.lastIndexOf('a',40));
		i=input.nextInt();
		System.out.println(s.lastIndexOf("string",50));
	}
}
