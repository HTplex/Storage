package string;
import java.util.Scanner;
public class StringC11sortstring {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=input.nextLine();
		char[] c=s.toCharArray();
		int [] a=new int[s.length()];
		for(int i=0;i<s.length();i++){
			a[i]=(int)(c[i]);
		}
		java.util.Arrays.sort(a);
		for(int i:a){
			System.out.print((char)(i));
		}
	}
}
