package neuqoj;
import java.util.Scanner;
public class Oj1093mirror {
	public static void main(String[]args) throws Exception{
		Scanner input=new Scanner(System.in);
		int i=input.nextInt();
		String[] s=new String[i];
		for(int i1=0;i1<i;i1++){
			s[i1]=input.next();
		}
		for(int i1=0;i1<i;i1++){
		while(s[i1].equals(reverse(s[i1]))&&s[i1].length()!=1&&s[i1].length()%2==0){
			StringBuilder ss=new StringBuilder(s[i1]);
		ss.delete(((int)((ss.length())/2)),ss.length());
		s[i1]=ss.toString();
		}
		
		
		}
		for(int i1=0;i1<i;i1++)
		System.out.println(s[i1].length());
	}
	public static String reverse(String a){
		StringBuilder b=new StringBuilder(a);
		b.reverse();
		return (b.toString());
	}
}
