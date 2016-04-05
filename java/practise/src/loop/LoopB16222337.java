package loop;
import java.util.Scanner;
public class LoopB16222337 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number you want to divide");
		int num=input.nextInt();
		int i=2;
		while (i<=(int)num){
		if (num%i==0){
			num/=i;
			System.out.print(i+" ");
		}//if
		else i++;
		}
	}
}
