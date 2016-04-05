package string;
import java.util.Scanner;
public class StringC08BtoD {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=new String(input.next());
		char[] c=s.toCharArray();
		long b=0;
		for(int i=0;i<c.length;i++){
			if(c[i]=='1')
			b+=(Math.pow(2,(c.length-i-1)));
		}
		System.out.println(b);
	}
}
