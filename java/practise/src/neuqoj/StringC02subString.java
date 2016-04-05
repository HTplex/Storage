package neuqoj;
import java.util.Scanner;
public class StringC02subString {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=input.next();
		String s2=input.next();
		System.out.println(test(s,s2));
	}
	public static boolean test(String a,String b){
		char[] c1=a.toCharArray();
		char[] c2=b.toCharArray();
		for(int i=0;i<b.length()-a.length()+1;i++){
			boolean bb=true;
			for(int ii=0;ii<a.length();ii++){
				if(c1[ii]!=c2[i+ii])
					bb=false;
			}
			if(bb) return bb;
		}
		return false;
	}
}
