package loop;
import java.util.Scanner;
public class LoopC21fin1 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("Loan Amount :");
		int LA=input.nextInt();
		System.out.print("Number of the year :");
		int time=input.nextInt();
		System.out.println("Interest Rate\tMonthly Payment\tTotal Payment");
		double i,TP,MP;
		for (i=5.0;i<=8.0;i+=0.125){
			MP=(LA*i/1200.0) /
				      (1 - (Math.pow(1 / (1 +i/1200.0), time * 12)));
			TP=MP*12*time;
			System.out.println(i+"%\t"+((int)(MP*100))/100.0+"\t"+((int)(TP*100))/100.0);
		}
	}
}
