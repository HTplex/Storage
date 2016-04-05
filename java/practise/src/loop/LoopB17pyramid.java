package loop;
import java.util.Scanner;
public class LoopB17pyramid {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number you want");
		int number=input.nextInt();
		int line=1;
		int str=1;
		while(number>line-1){
			if (number-str>line-1){
			System.out.print("    ");
			str++;
			continue;
			}//if
			else{ 
			System.out.printf("%4d",(Math.abs(number-str)+1));
			str++;
			}//else
			if (number-str<-line+1){
		str=1;
		line++;
		System.out.print("\n");
			}
		}//while
	}
}
