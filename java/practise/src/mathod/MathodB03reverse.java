package mathod;
import java.util.Scanner;
public class MathodB03reverse {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	System.out.println("please input the number");
	int a=input.nextInt();
	System.out.println("the reverse number is "+reverse(a));
	System.out.println("this number is a reverse number? "+isreverse(a));
	}
	public static String reverse(int a){
		int i;
		String s="";
		for(i=1000000000;i>a;i/=10){
			}
		while (i>0){
			char c=(char)('0'+(int)(a/i));
			a=a%i;
			s=c+s;
			i/=10;
		}
		return s;
	}
	public static String verse(int a){
		int i;
		String s="";
		for(i=1000000000;i>a;i/=10){
			}
		while (i>0){
			char c=(char)('0'+(int)(a/i));
			a=a%i;
			s=s+c;
			i/=10;
		}
		return s;
	}
	public static boolean isreverse(int a){
		boolean b=(reverse(a)==verse(a));
		return b;
	}
}
