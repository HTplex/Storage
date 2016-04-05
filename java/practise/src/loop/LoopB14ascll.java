package loop;
import java.util.Scanner;
public class LoopB14ascll {
	public static void main(String[]args){
		char a=0;
		for(int i=33;i<127;i++){
			a=(char)i;
			System.out.print(a);
			if (i%10==2)
				System.out.print("\n");
		}
	}
}
