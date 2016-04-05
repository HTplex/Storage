package choose;
import java.util.Scanner;
public class ChooseAaBoolean {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int A=(int)(System.currentTimeMillis()%10);
		int B=(int)(System.currentTimeMillis()*7%10);
		System.out.print("What is "+A+"+"+B+"?");
		int a=input.nextInt();
		boolean x=A+B==a;
		System.out.print(A+"+"+B+"="+a+"  "+x);
		
	}
}
