package string;
import java.util.Scanner;
public class StringB13change {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s="hello ";
		StringBuilder b=new StringBuilder("hello ");
		System.out.println(s+"\n"+b);
		s+="java";
		b.append("java");
		System.out.println(s+"\n"+b);
	}
}
