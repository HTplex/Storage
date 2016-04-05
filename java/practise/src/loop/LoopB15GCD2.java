package loop;
import java.util.Scanner;
public class LoopB15GCD2 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input the first number");
		int a=input.nextInt();
		System.out.print("please input the second number");
		int b=input.nextInt();
		int c=Math.min(a,b);
		while(c>1){
			if(a%c==0&&b%c==0){
				System.out.println(c);
				break;
				}
				c--;	
		}
	}
}
