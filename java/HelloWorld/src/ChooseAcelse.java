
import java.util.Scanner;
public class ChooseAcelse {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	System.out.print("please input your score");
	int x=input.nextInt();
	if (x>=60){
		System.out.println("you are passed !");
		}
	else{
		System.out.println("you are failed");
	}
	}
}
