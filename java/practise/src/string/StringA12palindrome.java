package string;
import java.util.Scanner;
public class StringA12palindrome {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("input underchecked word");
		String s=input.next();
		char[] c=s.toCharArray();
		int l=c.length;
		boolean b=true;
		for(int i=0;i<(int)(l/2);i++){
			if(c[i]!=c[l-1-i])
			b=false;
		}
		System.out.println(b);
	}
}
