package choose;
import java.util.Scanner;
public class ChooseAbbif {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	System.out.print("input your total score");
	int x=input.nextInt();
	if (x>=90){
		System.out.print("you are so great !");
		}
	if (x<60){
		System.out.print("you are so lame !");
		}
	}
}
