package loop;
import java.util.Scanner;
public class LoopB24pi {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input the number you want");
		long num=input.nextInt();
		double pi=0;
		for (long i=1;i<2*num+3;i+=2){
		pi+=4*(Math.pow(-1,(i-1)/2)*(1.0/i));	
		}
		System.out.print(pi);
	}
}
