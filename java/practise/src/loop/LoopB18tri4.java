package loop;
import java.util.Scanner;
public class LoopB18tri4 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the line you want");
		int line=1;
		int i=1;
		int linelimit=input.nextInt();
		while (line<=linelimit){
			while(i<=linelimit){
				if (line>i){
					System.out.print("    ");
				}//if
				else {
					System.out.printf("%4d",i-line+1);
				}//else
				i++;
			}//horizontal
			line++;
			i=1;
			System.out.println();
		}//straight
	}
}
