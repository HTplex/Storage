package array;
import java.util.Scanner;
public class ArrayB23locker {
	public static void main(String[]args){
		boolean [] locker=new boolean [100];
		for (int i=1;i<=100;i++){
			for (int m=0;m<locker.length;m++){
			if(m%i==0)
				locker[m]=(!locker[m]);
		}
		}
			for(int i=0;i<locker.length;i++){
				if(locker[i])
					System.out.print("O");
				else
					System.out.print("-");
				if(i%10==9)
					System.out.println();
			
		}
	}
}
