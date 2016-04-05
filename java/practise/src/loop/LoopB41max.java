package loop;
import java.util.Scanner;
public class LoopB41max {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int inp=-1;
		int a=-1;
		int i=0;
		while (inp!=0){
			System.out.print("please input what ever you want");
			inp=input.nextInt();
			if (inp>a)
				a=inp;
			else if (inp==a)
				i++;
		}
		System.out.print("the number is :"+a+"\nthe number of the number is:"+i);
	}
}
