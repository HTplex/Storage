package string;
import java.util.Scanner;
public class StringA10tostring {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int i=415;
		double d=Math.PI;
		boolean b=true;
		float f=3.1415f;
		long l=System.currentTimeMillis();
		char[] c={'a','i','o',' '};
		String a=new String("i am another string i really dont know what is this program for");
		int i2=input.nextInt();
		System.out.println(a);
		i2=input.nextInt();
		System.out.println(String.valueOf('s'));
		i2=input.nextInt();
		System.out.println(String.valueOf(c));
		i2=input.nextInt();
		System.out.println(String.valueOf(d));
		i2=input.nextInt();
		System.out.println(String.valueOf(i));
		i2=input.nextInt();
		System.out.println(String.valueOf(f));
		i2=input.nextInt();
		System.out.println(String.valueOf(l));
		i2=input.nextInt();
		System.out.println(String.valueOf(b));
	}
}
