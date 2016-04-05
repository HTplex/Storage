package choose;
import java.util.Scanner;
public class ChooseAdififififif {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	System.out.print("please input your score");
	int score=input.nextInt();
	char rate;
	if (score==100) rate='S';
	else if(score>=90) rate='A';
	else if(score>=80) rate='B';
	else if(score>=70) rate='C';
	else if(score>=60) rate='D';
	else rate='F';
	System.out.println("Your rate is   "+rate);
	}
}
