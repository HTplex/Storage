package loop;
import java.util.Scanner;
public class LoopB19pyramidplus {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the linelimit");
		int linelimit=input.nextInt();
		int line=1;
		int i=1;
		int output;
		while(line<=linelimit){
			while(line+linelimit-1>=i){
			if (line+i-linelimit<=0)
				System.out.print("    ");
				else
					System.out.printf("%4d",(int)(Math.pow(2,line-(Math.abs(linelimit-i))-1)));
			i++;
			}//while
				System.out.println();
					line++;
					i=1;
			
		}
	}
}
