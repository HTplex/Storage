package string;
import java.util.Scanner;
public class StringA14count {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=input.next();
		int [] count=new int[26];
		char[] art=s.toCharArray();
		for(int i=0;i<art.length;i++){
			Character.toLowerCase(art[i]);
			count[(int)(art[i])-97]++;
		}
		for(int i=0;i<26;i++){
		System.out.println((char)(i+97)+": "+count[i]);	
		}
	}
}
