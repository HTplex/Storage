package practise;
import java.util.Scanner;

public class TwopraiticeI {
public static void main (String[]args){
	Scanner input=new Scanner(System.in);
	System.out.print("please input the mount you want to cash (without the dot)");
	int cash=input.nextInt();
	int hectodollar=(int) (cash/10000);
	int hectoremain=cash%(10000);
	int helfhectodollar=(int)(hectoremain/5000);
	int helfhectoremain=hectoremain%(5000);
	int didecadollar=(int)(helfhectoremain/2000);
	int didecaremain=helfhectoremain%(2000);
	int decadollar=(int)(didecaremain/1000);
	int decaremain=didecaremain%(1000);
	int quinqudollar=(int)(decaremain/500);
	int quinquremain=decaremain%(500);
	int monodollar=(int)(quinquremain/100);
	int monoremain=quinquremain%(100);
	int halfdollar=(int)(monoremain/50);
	int halfremain=monoremain%(50);
	int decidollar=(int)(halfremain/10);
	int deciremain=halfremain%(10);
	int quinqupenny=(int)(deciremain/5);
	int quinperemain=deciremain%(5);
	int penny =(int)(quinperemain/1);
	System.out.println("the cash is\n"+hectodollar+"*$100\n"+helfhectodollar+"*$50\n"+didecadollar
			+"*$20\n"+decadollar+"*$10\n"+quinqudollar+"*$5\n"+monodollar+"*$1\n"+halfdollar+
			"*$0.5\n"+decidollar+"*$0.1\n"+quinqupenny+"$0.05\n"+penny+"*$0.01");
		
}
}
