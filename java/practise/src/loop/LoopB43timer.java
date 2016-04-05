package loop;
import java.util.Scanner;
public class LoopB43timer {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the time left");
		int lim=input.nextInt();
		long limm=System.currentTimeMillis();
		int i=1;
		while (i<=lim){
			if ((System.currentTimeMillis()-limm)==1000*i){
				if(lim-i==1)System.out.println("1 Second remaining");
				else if (lim==i)System.out.println("time is up");
				else System.out.println(lim-i+" Seconds remaining");
				i++;
			}
			else continue;
		}
		
	}

}
