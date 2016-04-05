package string;
import java.util.Scanner;
public class StringA13charactermathod {
public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	Character a=new Character('a');
	int i=input.nextInt();
	System.out.println(a.charValue());
	i=input.nextInt();
	System.out.println(a.compareTo('c'));
	i=input.nextInt();
	System.out.println(a.equals('c'));
	i=input.nextInt();
	System.out.println(Character.isDigit(a));
	i=input.nextInt();
	System.out.println(Character.isLetter(a));
	i=input.nextInt();
	System.out.println(Character.isLetterOrDigit(a));
	i=input.nextInt();
	System.out.println(Character.isLowerCase(a));
	i=input.nextInt();
	System.out.println(Character.toUpperCase(a));
}
}
