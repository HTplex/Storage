package loop;
import java.util.Scanner;
public class LoopB18tri3 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the line you want");
		int linelimit=input.nextInt();
		int i=1;
		int line=1;
		while (line<=linelimit){
			while (i<=linelimit){
				if (linelimit-line-i>=0){
					System.out.print("    ");
				}//if
				else {
					System.out.printf("%4d",linelimit-i+1);
				}
				i++;
			}//horizontal
		i=1;
		line++;
		System.out.println();
		}//vertical
	}
}
