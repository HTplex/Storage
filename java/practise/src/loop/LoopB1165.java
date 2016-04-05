package loop;
import java.util.Scanner;
public class LoopB1165 {
	public static void main(String[]args){
		int s=0;
		for (int i=100;i<201;i++){
			if((i%5==0||i%6==0)&&(i%30!=0)){
				System.out.print(i+" ");
				s++;
				if (s%10==0){
					System.out.print("\n");
				}
			}
		}
	}
}
