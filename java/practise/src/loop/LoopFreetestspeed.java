package loop;
import java.util.Scanner;
public class LoopFreetestspeed {
	public static void main(String[]args){
		long timestart=System.currentTimeMillis();
		int a=1;
		for (long timeover=System.currentTimeMillis();timeover-timestart<=1000;timeover=System.currentTimeMillis()){//no ";"
			System.out.println(a);
			a++;
		}
	}
}