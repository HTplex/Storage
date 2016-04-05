package loop;
import java.util.Scanner;
public class LoopB31niceCD{
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input the value of this cd");
		int value=input.nextInt();
		System.out.println("please input how long you want to keep it");
		int time=input.nextInt();
		System.out.println("MonthCD\t\tValue");
		double total=value;
		double rate=5.75;
		for (int mon=1;mon<=time;mon++){;
			total*=(1+rate/1200.0);
			System.out.println(mon+"\t\t"+((int)(total*100))/100.0);
		}
	}
}
