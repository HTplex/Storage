package loop;
import java.util.Scanner;
public class LoopB05pokgkgpo {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("kg    po    |po    kg");
		for (int i=1;i<200;i+=2){
			int a=1;
			int p=22*i;
			int k=5*a+15;
			int pp=(int)((k/2.2)*100);
			System.out.println(i+"    "+p/10.0+"   |"+k+"    "+pp/100.0);
			a++;
		}
	}
}
