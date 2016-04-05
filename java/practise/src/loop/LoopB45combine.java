package loop;
import java.util.Scanner;
public class LoopB45combine {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input the number a");
		int a=input.nextInt();
		System.out.print("please input the number b");
		int b=input.nextInt();
		int i;
		for (i=a;i<=b;i++){
			for (int ii=a;ii<=b;ii++){
				if (i<ii){
				System.out.printf("%8d",i);
				System.out.printf("%8d",ii);
				System.out.println();
				}
			}
		}
	}
}
