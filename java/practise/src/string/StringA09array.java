package string;
import java.util.Scanner;
public class StringA09array {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=new String("iamashortstring");
		int i=input.nextInt();
		System.out.println(s);
		String s1=new String("shorter");
		i=input.nextInt();
		System.out.println(s1);
		char[] a=s.toCharArray();
		i=input.nextInt();
		for (char i1:a)
			System.out.print(i1+" ");
		i=input.nextInt();
		s1.getChars(2, 6, a, 10);
		for (char i1:a)
			System.out.print(i1+" ");
	}
}
