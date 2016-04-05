package string;
import java.util.Scanner;
public class StringA05substring {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String ss=new String("welcome to java");
		System.out.println(ss);
		System.out.print("please input the location you wanna start to change");
		int i=input.nextInt();
		System.out.println("please input the string you wanna to change with");
		String s=input.next();
		System.out.println(ss.substring(0,i)+s);
	}
}
