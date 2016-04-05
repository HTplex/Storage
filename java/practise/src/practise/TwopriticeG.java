package practise;
import java.util.Scanner;
public class TwopriticeG {
	public static void main(String[]args){
		System.out.println("Enter a number of minutes");
		Scanner input=new Scanner(System.in);
		int minute=input.nextInt();
		int year=minute/(60*24*365);
		int dayremain=(minute%(60*24*365))/(60*24);
		System.out.println(minute+" minutes is approximately "+year+"years and "+dayremain+"days.");
	}	

}
