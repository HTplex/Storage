package loop;
import java.util.Scanner;
public class LoopB19tri2 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int line=1;
		int i=1;
		System.out.println("please input the total line you want");
		int linelimit=input.nextInt();
		while (line<=linelimit) {
			while (i<=linelimit){
				System.out.printf("%4d",i);
				i++;
			}
			i=1;
			System.out.println();
			linelimit--;
		}
		
	}
}
