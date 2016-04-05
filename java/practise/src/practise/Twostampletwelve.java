package practise;
import java.util.Scanner;
public class Twostampletwelve {
	public static void main (String[]args){
		Scanner input= new Scanner(System.in);
		System.out.print("please input annual interest rate, number of years and total loan amount in order");
			double AIR=input.nextDouble();
			double Y=input.nextDouble();
			double TL=input.nextDouble();
			double MIR=AIR/12;
			double mpay=(TL*MIR)/(1-(Math.pow(Math.pow((1+MIR),12*Y),-1)));
			double tpay=mpay*Y*12;
		System.out.print("the payment for each month is"+(int)(mpay*100)/100.0+"total pament is"+(int)(tpay*100)/100.0);
	}
}
