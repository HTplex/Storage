package string;
import java.util.Scanner;
public class StringC06countabc {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=new String(input.next());
		s=s.toUpperCase();
		int[] a=new int[26];
		for(int i=0;i<s.length();i++){
			Character c=new Character(s.charAt(i));
			if(c.isLetter(c))
			a[(int)(c)-'A']++;
		}
		for(int i=0;i<a.length;i++)
		System.out.println(((char)('A'+i))+" "+a[i]);
	}
}
