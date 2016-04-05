package string;
import java.util.Scanner;
public class StringC10DtoB {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		long i=input.nextLong();
		String b=new String();
		while(i>=1){
			if(i%2!=0)
				b="1"+b;
			else b="0"+b;
			i/=2;
		}
		System.out.println(b);
	}
}
