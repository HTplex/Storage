package loop;
import java.util.Scanner;
public class LoopAjGCD {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input ");
		int aa=input.nextInt();
		int bb=input.nextInt();
		int a=aa;
		int b=bb;
		int d=0;
		int cc=0;
		if (a==b)
			System.out.print("are you kidding me?");
		else{
			do {
				if (a>b){
					int c=b;
					c=a-b;
					a=b;
					b=c;
					cc=c;
				}//if
				else {
					int c=a;
					c=b-a;
					b=a;
					a=c;
					cc=c;
				}//else
			}//do
				
			while(a%cc!=0||b%cc!=0);
		}
		System.out.print("the ged of "+aa+" and "+bb+" is "+cc);
		
	}
}
