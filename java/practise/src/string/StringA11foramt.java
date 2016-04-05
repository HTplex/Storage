package string;
import java.util.Scanner;
public class StringA11foramt {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=new String("here i am again, i recoginze you, you are a great girl");
		String[] tokens=s.split(" ");
		System.out.println(tokens.length);
		String sf=String.format("%8s%8s%8s%8s%8s%8s%8s%8s%8s%8s%8s%8s", tokens[0],tokens[1],tokens[2],tokens[3],tokens[4],tokens[5],tokens[6],tokens[7],tokens[8],tokens[9],tokens[10],tokens[11]);
		
		System.out.println(sf);
	}
}
