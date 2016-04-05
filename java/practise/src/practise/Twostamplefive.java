package practise;
import java.util.Scanner;
public class Twostamplefive {
public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	System.out.print("Please input the number of second you want");
	int second=input.nextInt();
	int hour=second/3600;
	int minute=(second%3600-second%60)/60;
	int remainseconds=second%60;
	System.out.println("the transformed time is"+hour+"Hour(s)"
					   +minute+"Minute(s)"+remainseconds+"Second(s)");
	}

}
