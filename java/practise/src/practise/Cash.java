package practise;
import java.util.Scanner;

public class Cash {
public static void main (String[]args){
	Scanner input=new Scanner(System.in);
	System.out.print("please input the mount you want to cash");
	double cash=input.nextDouble();
	int hectodollar=(int) (cash/100.0);
	double hectoremain=cash%(100.0);
	int helfhectodollar=(int)(hectoremain/50.0);
	double helfhectoremain=hectoremain%(50.0);
	int didecadollar=(int)(helfhectoremain/20.0);
	double didecaremain=helfhectoremain%(20.0);
	int decadollar=(int)(didecaremain/10.0);
	double decaremain=didecaremain%(10.0);
	int quinqudollar=(int)(decaremain/5.0);
	double quinquremain=decaremain%(5.0);
	int monodollar=(int)(quinquremain/1.0);
	double monoremain=quinquremain%(1.0);
	int halfdollar=(int)(monoremain/0.5);
	double halfremain=monoremain%(0.5);
	int decidollar=(int)(halfremain/0.1);
	double deciremain=halfremain%(0.1);
	int quinqupenny=(int)(deciremain/0.05);
	double quinperemain=deciremain%(0.05);
	int penny =(int)(quinperemain/0.01);
	System.out.println("the cash is\n"+hectodollar+"*$100\n"+helfhectodollar+"*$50\n"+didecadollar
			+"*$20\n"+decadollar+"*$10\n"+quinqudollar+"*$5\n"+monodollar+"*$1\n"+halfdollar+
			"*$0.5\n"+decidollar+"*$0.1\n"+quinqupenny+"$0.05\n"+penny+"*$0.01");
		
}
}
