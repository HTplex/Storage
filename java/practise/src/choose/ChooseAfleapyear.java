package choose;
import java.util.Scanner;
public class ChooseAfleapyear {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input a year ");
		int x=input.nextInt();
		boolean isleapyear=((x%400==0)||((x%100!=0)&&(x%4==0)));
		System.out.println("the year "+x+" is a leapyear?"+"\n--the answer is "+isleapyear);
		
			
	}
}
