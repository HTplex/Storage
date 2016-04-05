
import java.util.Scanner;
public class ChooseAgswitch {
public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	System.out.print("please input your lottery number");
	int lottery=input.nextInt();
	switch (lottery){
	case 60:System.out.print("first price!");
	break;
	case 24:System.out.print("Second price!");
	break;
	case 19:System.out.print("Second price!");
	break;
	case 79:System.out.print("Second price!");
	break;
	case 18:System.out.print("third price!");
	break;
	case 9:System.out.print("third price!");
	break;
	case 46:System.out.print("third price!");
	break;
	case 25:System.out.print("third price!");
	break;
	case 82:System.out.print("third price!");
	break;
	default:System.out.print("sorry you dont have a price");
	}
}
}
