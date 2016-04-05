package string;
import java.util.Scanner;
public class StringA16palidromeplus {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String a=input.next();
		StringBuilder s=new StringBuilder();
		char [] c1=a.toCharArray();
		for(int i=0;i<c1.length;i++){
			Character cc=new Character(c1[i]);
			if (Character.isLetterOrDigit(cc))
		s.append(cc);
		}
		String s1=s.toString();
		s.reverse();
		String s2=s.toString();
		int e;
		e=Integer.parseInt(s2);

		System.out.print(s1.equals(s2));
	}
}
