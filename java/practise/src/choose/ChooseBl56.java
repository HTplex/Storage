package choose;
import java.util.Scanner;
public class ChooseBl56 {
	public static void main(String[]args){
		Scanner input= new Scanner(System.in);
		System.out.print("please input a integer");
		int x=input.nextInt();
		if (x%5==0&&x%6==0)
			System.out.print(x+" is divisible by 5 or 6");
		else if (x%5==0||x%6==0)
			System.out.print(x+" is divisible by both 5 and 6, but not both");
		else 
			System.out.print(x+" is divisible by either 5 or 6");
	}
}
