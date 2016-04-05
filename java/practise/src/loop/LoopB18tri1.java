package loop;
import java.util.Scanner;
public class LoopB18tri1 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int currline=1;
		int i=1;
		System.out.print("please input the number you want ");
		int line=input.nextInt();
		while (currline<=line){
			while(i<=currline){
				System.out.printf("%4d",i);
				i++;
			}//while2
			i=1;
			currline++;
			System.out.println();
		}//while1
	}
}
