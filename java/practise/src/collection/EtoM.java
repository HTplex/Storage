package collection;
import java.util.Scanner;
public class EtoM {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=input.nextLine();
		char[] c=s.toCharArray();
		for(int i=0;i<c.length;i++){
			int i1=(int)(c[i]);
			System.out.print(Integer.toBinaryString(i1)+" ");
		}
	}
}
