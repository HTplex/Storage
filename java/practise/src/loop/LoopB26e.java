package loop;
import java.util.Scanner;
public class LoopB26e {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input whatever you want");
		int in=input.nextInt();
		double sum=1;
		for (int i=1;i<in;i++){
		sum/=(in-i);
		sum+=1;
		}
	System.out.println(sum);
	}	
}
