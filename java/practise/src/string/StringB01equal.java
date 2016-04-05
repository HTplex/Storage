package string;
import java.util.Scanner;
public class StringB01equal {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String a="welcome";
		String b="welcome";
		String c=new String("welcome");
		String d=new String("welcome");
		int i=input.nextInt();
		System.out.println(a==b);
		i=input.nextInt();
		System.out.println(c==d);
		i=input.nextInt();
		System.out.println(a==d);
		i=input.nextInt();
		System.out.println(c.equals(d));
		i=input.nextInt();
		System.out.println(c.compareTo(d));
		i=input.nextInt();
		System.out.println(c.compareTo(a));
		i=input.nextInt();
		System.out.println(c.charAt(1));
		i=input.nextInt();
		System.out.println(c.indexOf('o'));
		i=input.nextInt();
		System.out.println(c.lastIndexOf('e'));
		i=input.nextInt();
		System.out.println(c.lastIndexOf('e',5));
		i=input.nextInt();
		System.out.println(c.length());
		i=input.nextInt();
		System.out.println(c.substring(4));
		i=input.nextInt();
		System.out.println(c.substring(4,7));
		i=input.nextInt();
		System.out.println(c.startsWith("l"));
		i=input.nextInt();
		System.out.println(c.endsWith("l"));
		i=input.nextInt();
		System.out.println(c.toLowerCase());
		i=input.nextInt();
		System.out.println(c.toUpperCase());
		i=input.nextInt();
		System.out.println(c.trim());
		i=input.nextInt();
		System.out.println(c.replace('c','o'));
		i=input.nextInt();
		System.out.println(c.replaceAll("[e,o]","haha"));
		i=input.nextInt();
		System.out.println(c.replaceFirst("[e.o]","haha"));
		i=input.nextInt();
		char[] m=c.toCharArray();
		for(char p:m)
			System.out.println(p);
	}
}
