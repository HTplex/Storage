package neuqoj;
import java.util.Scanner;
public class Maina {
	public static void main(String[]args){
		int[] s={87,97,110,32,115,104,97,110,103,32,57,32,100,105,97,110,32,121,111,117,32,104,117,105};
		char[] c=new char[s.length];
		for(int i=0;i<s.length;i++){
			c[i]=(char)(s[i]);
		}
		for(char i:c){
			System.out.print(i);
		}
			System.out.println();
	}
}
 