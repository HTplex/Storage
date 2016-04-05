package choose;
import java.util.Scanner;
public class ChooseBbeven2 {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in); 
		System.out.print("please input a integer");
	int x=input.nextInt();
	String a=(x%2==0?"is a even number":"is a odd number");
	System.out.println("The number "+x+" "+a);
	}
}
