package string;
import java.util.Scanner;
public class StringC12anagram {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s1=input.nextLine();
		String s2=input.nextLine();
		s1=sortstring(s1);
		s2=sortstring(s2);
		System.out.println(s1.equals(s2));
	}
	public static String sortstring(String s){
		char[] c=s.toCharArray();
		int [] a=new int[s.length()];
		for(int i=0;i<s.length();i++){
			a[i]=(int)(c[i]);
		}
		java.util.Arrays.sort(a);
		String ss=new String();
		for(int i:a)
		ss=ss+(char)(i);
		return ss;
	}
}
