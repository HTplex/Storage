package practise;
import java.util.Scanner;
public class Stringc {
public static void main (String[]args){
	Scanner input= new Scanner(System.in);
	System.out.print("please input a sentence");
	String s=input.nextLine();
	System.out.print("please input another sentence");
	String b=input.nextLine();
	System.out.println("the mixed sentence is "+s+" "+b);
	
}

}
