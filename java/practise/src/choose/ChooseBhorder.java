package choose;
import java.util.Scanner;
public class ChooseBhorder {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);	
		int num1=input.nextInt();
		int num2=input.nextInt();
		int num3=input.nextInt();
		int num4;
	{if (num1>num2){
		num4=num2;
		num2=num1;
		num1=num4;}
		if(num2>num3){
			if (num1>num3){
			num4=num3;
			num3=num2;
			num2=num1;
			num1=num4;}
			else {num4=num2;
				num2=num3;
				num3=num4;}}
			System.out.println(num3+" "+num2+" "+num1);
		}
	}
}
