package loop;
import java.util.Scanner;
public class LoopB02plus {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("welcome to calculation app \ninput 0 to start");
		int start=input.nextInt();
		int sum=0;
		long starttime=0;
		if (start==0)
			starttime=System.currentTimeMillis();
		for(int i=0;i<10;i++){
			int a=(int)(15*Math.random());
			int b=(int)(15*Math.random());
			System.out.println("please input the answer of "+a+"+"+b);
			int c=input.nextInt();
			if (c==a+b){
				sum++;
			}//if++
		}
			long overtime=System.currentTimeMillis();
			System.out.println(sum+"of 10 questions is right");
			System.out.println("total time is "+(overtime-starttime)/1000);
	
	}
}
