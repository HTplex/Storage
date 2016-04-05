package loop;
import java.util.Scanner;
public class LoopB40coin {
	public static void main(String[]args){
		int k1=0;
		int k2=0;
		int k=0;
		for (int i=0;i<1000000;i++){
			int coin=(int)(2*Math.random());
			System.out.print(coin);
			if (coin==1)
				k1++;
			else k2++;
			k++;
			if(k%1000==0)System.out.println();
		}
		System.out.println("\nfornt: "+k1+"back: "+k2);
	}
}
