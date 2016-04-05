package loop;
import java.util.Scanner;
public class LoopB04mikm {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("mi     km");
		for(int i=1;i<=10;i++){
			double k=1.609*i;
			System.out.println(i+"     "+k);
		}
	}
}
