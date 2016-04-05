package loop;
import java.util.Scanner;
public class LoopB1056 {
	public static void main(String[]args){
		int s=0;
		for (int i=100;i<1001;i++){
			if (i%5==00&&i%6==0){
				System.out.print(i+" ");
				s++;
				if (s%10==0){
					System.out.print("\n");
				}
			}
		}
	}
}
