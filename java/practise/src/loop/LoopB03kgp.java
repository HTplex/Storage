package loop;
import java.util.Scanner;
public class LoopB03kgp {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("kg     pound");
		int m=1;
		for (int i=1;i<200;i++){
			int j=22*m;
			System.out.println(m+"     "+j/10.0);
			m++;
		}
	}
}
