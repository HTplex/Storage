package loop;
import java.util.Scanner;
public class LoopAkedufee {
	public static void main(String[]args){
		int t=0;
		for (double tuition=10000;tuition<20000;tuition*=1.07){
			t++;
		}
		System.out.println(t);
	}
}
