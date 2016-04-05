package neuqoj;
import java.util.Scanner;
public class StringC04count {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=input.next();
		String b=input.next();
		char c=b.charAt(0);
		System.out.println(count(s,c));
	}
	public static int count(String s, char c){
		int sum=0;
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)==c)
				sum++;
		}
		return sum;
	}
}
