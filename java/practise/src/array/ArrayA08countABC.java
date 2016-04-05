package array;
import java.util.Scanner;
public class ArrayA08countABC {
	public static void main(String[]args){
		char[] abc=new char[100000];
		for (int i=0;i<abc.length;i++)
			abc[i]=mathod.MathodA13char.chara();
		int[] count=new int [26];
		for (int i=0;i<abc.length;i++){
		count[(int)(abc[i]-'a')]+=1;	
		}
		System.out.println("the letters are:");
		int line=1;
		for(char i:abc){
			System.out.print(i+" ");
			line++;
			if (line%50==0)
				System.out.println();
		}
		line=0;
		System.out.println("the occurrences of each letter are:");
			for (int i:count){
				System.out.print((char)('a'+line)+" ");
				System.out.print(i+"   ");
				line++;
			
		}
	}
}
