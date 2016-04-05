package string;
import java.util.Scanner;
public class StringC07cellphone {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=new String(input.next());
		for(int i=0;i<s.length();i++){
			Character a=new Character(s.charAt(i));
			if(a.isLetter(a.charValue()))
			System.out.print(getnumber(a));
			else
			System.out.print(a);
		}
	}
	public static int getnumber(Character c){
		String[] s={"ABC","DEF","GHI","JKL","MNO","PQRS","TUV","WXYZ"};
		for(int i=0;i<9;i++){
		if(inString(c,s[i]))
			return i+1;
		}
		return 0;
	}
	public static boolean inString(Character c, String s){
		boolean b=false;
		for(int i=0;i<s.length();i++){
			if(c==s.charAt(i)){
				b=true;
				return b;
			}
		}
		return b;
	}
}
