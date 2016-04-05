package string;
import java.util.Scanner;
public class StringB04basic {
	public static void main(String[]args){
		String s1=new String("h e h e");
		String s2=new String("H E H e");
		boolean b=s1.equals(s2);
		b=s1.equalsIgnoreCase(s2);
		int i=s1.compareTo(s2);
		i=s1.compareToIgnoreCase(s2);
		b=s1.startsWith("aaa");
		b=s1.endsWith("aaa");
		i=s1.length();
		char c=s1.charAt(0);
		String s4=s1+s2;
		s4=s1.substring(1);
		s4=s1.substring(1,4);
		s4=s1.toLowerCase();
		s4=s1.toUpperCase();
		s4=s1.trim();
		s4=s1.replace('c','E');
		s4=s1.replaceAll("c","efuiae");/*x*/
		String[] m=s1.split(" ");
		i=s1.indexOf('e');
		i=s1.lastIndexOf("abc");
	}
}
