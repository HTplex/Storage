package choose;
import java.util.Scanner;
public class ChooseBetriplus {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int a=(int)(100*(Math.random()));
		int b=(int)(100*(Math.random()));
		int c=(int)(100*(Math.random()));
		System.out.println("please input the answer of "+a+"+"+b+"+"+c);
		int d=input.nextInt();
		System.out.println(d==a+b+c);
	}
}
