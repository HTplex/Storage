package loop;
import java.util.Scanner;
public class LoopB272008 {
	public static void main(String[]args){
		int count=0;
		for (int i=2001;i<2101;i++){
			if (i%100==0){
				if (i/400==0){
					System.out.print(i+" ");
					count++;
				}//
			}//100
			else {
				if(i%4==0){
			
				System.out.print(i+" ");
				count++;
			}//else
			else continue;
			}
		if (count%10==0)
			System.out.println();
		else continue;
		}//for
	}
}
