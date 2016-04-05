package loop;
import java.util.Scanner;
public class LoopB33niceword {
	public static void main(String[]args){
		for(int i=1;i<=10000;i++){
			int totl=0;
			for(int a=1;a<i;a++){
			if (i%a==0)
				totl+=a;
		}//for
			if (totl==i)
				System.out.println(i);
		}//for
		}
}
