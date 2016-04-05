package choose;
import java.util.Scanner;
public class ChooseBnlottery {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int a=(int)(1000*Math.random());
		int a1=(int)(a/100);
		int a2=(int)((a%100)/10);
		int a3=(int)(a%10);
		System.out.println("please input a Three-digit");
		int x=input.nextInt();
		int x1=(int)(x/100);
		int x2=(int)((x%100)/10);
		int x3=(int)(x%10);
		if (x==a) System.out.print("congratulation! you win a price of $10,000");
		else if (x==100*a1+10*a2+a3||x==100*a1+10*a3+a2||x==100*a2+10*a1+a3||x==100*a2+10*a3+a1||x==100*a3+10*a2+a1||x==100*a3+10*a1+a2)
			System.out.print("nice! you win a price of $3,000");
		else if (x1==a1||x1==a2||x1==a3||x2==a1||x2==a2||x2==a3||x3==a1||x3==a2||x3==a3)
			System.out.println("yes! you win a price of $1,000");
		else System.out.println("Sorry, you just missed the price");
		
	}
}
