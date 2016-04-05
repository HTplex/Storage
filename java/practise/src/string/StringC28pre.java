package string;
import java.util.Scanner;
public class StringC28pre {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s1=input.next();
		String s2=input.next();
		for(int i=0;i<=s1.length()&&i<=s2.length();i++){
		String pre1=s1.substring(0,i);
		String pre2=s2.substring(0,i);
		if(!pre1.equals(pre2)){
		StringBuilder p=new StringBuilder(pre1);
		p.deleteCharAt(p.length()-1);
		System.out.println(p.toString());
		System.exit(0);
		}
		}
		System.out.println("nothing");
	}
}
