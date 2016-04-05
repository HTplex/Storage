package practise;
	import java.util.Scanner;
public class Stringb {
	public static void main (String[]args){
		Scanner input= new Scanner(System.in);
		System.out.print("please input a 3 word sentence");
		String firstword=input.next();
		String secondword=input.next();
		String thirdword=input.next();
		System.out.println("the first word is\""+firstword+
				"\"\nthe second word is\""+secondword+
				"\"\nthe third word is\""+thirdword+"\"");
		
	}

}
