package loop;
import java.util.Scanner;
public class LoopB28sun {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String display;
		System.out.print("please input the first day number");
		int i=input.nextInt();
		String disply="";
		for (int month=1;month<=12;month++){
		switch(month){
		case 1:System.out.print("jun 1st is");break;
		case 2:System.out.print("feb 1st is");i+=31;break;
		case 3:System.out.print("mar 1st is");i+=28;break;
		case 4:System.out.print("apr 1st is");i+=31;break;
		case 5:System.out.print("may 1st is");i+=30;break;
		case 6:System.out.print("june 1st is");i+=31;break;
		case 7:System.out.print("jul 1st is");i+=+30;break;
		case 8:System.out.print("aug 1st is");i+=31;break;
		case 9:System.out.print("sep 1st is");i+=31;break;
		case 10:System.out.print("oct 1st is");i+=30;break;
		case 11:System.out.print("nov 1st is");i+=31;break;
		case 12:System.out.print("dec 1st is");i+=30;break;
		}
		switch (i%7){
		case 1:disply="Monday";
		break;
		case 2:disply="Tuesday";
		break;
		case 3:disply="Wednesday";
		break;
		case 4:disply="Thresday";
		break;
		case 5:disply="Friday";
		break;
		case 6:disply="Saturday";
		break;
		case 0:disply="Sunday";
		break;
		}
		System.out.println(" "+disply);
		}
	}
}
