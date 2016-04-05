package loop;
import java.util.Scanner;
public class LoopB30losemoney {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input the total money");
		double money=input.nextDouble();
		System.out.println("please input the annualinterest");
		double AI=input.nextDouble();
		System.out.println("please inpht the total time you want to save");
		int t=input.nextInt();
		double total=0;
		for (int i=1;i<=t;i++){
			total+=money;
			total*=(1+AI/(1200.0));
		}
		System.out.println((int)(total*1000)/1000);
	}
}
