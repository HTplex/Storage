package lab;
import java.util.Scanner;
public class Compabc {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int a=input.nextInt();
		int b=input.nextInt();
		int c=input.nextInt();
		System.out.println(a>b?b>c?"abc":c>a?"cab":"acb":c>a?c>b?"cba":"bca":"bac");
		input.close();
	}
}
