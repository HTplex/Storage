package loop;
import java.util.Scanner;
public class LoopB39roylaties {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		double mon=0.0;
		double fin=0.0;
		while (fin<=30000){
			if (mon<=5000.0){
				fin=(0.08)*mon+5000;
			}
			else if(mon<=10000.0){
				fin=(0.1)*(mon)+5000;
			}
			else fin=(0.12)*(mon)+5000;
			mon+=0.01;
		}
		System.out.print(((int)(mon*100))/100.0);
		}
}
