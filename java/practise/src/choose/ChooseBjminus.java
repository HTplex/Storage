package choose;
import java.util.Scanner;
public class ChooseBjminus {
	public static void main (String[]args){
		Scanner input=new Scanner(System.in);
		int a=(int)(100*Math.random());
		int b=(int)(100*Math.random());
		int d;
		if (a>b) {
			d=b;
			b=a;
			a=d;
		}
		System.out.print("please input the answer of "+b+"-"+a);
		int c=input.nextInt();
		System.out.println(b-a==c);
	}
}
