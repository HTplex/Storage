package choose;
import java.util.Scanner;
public class ChooseBdplus {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	int a=(int)(100*Math.random());
	int b=(int)(100*Math.random());
	System.out.print("please input the answer of "+a+"+"+b);
	int c=input.nextInt();
	System.out.println(c==a+b);
	}
}
