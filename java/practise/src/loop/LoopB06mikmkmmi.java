package loop;
import java.util.Scanner;
public class LoopB06mikmkmmi {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("mi    km   |km    mi");
		for (int i=1;i<11;i++){
			int k=1609*i;
			int kk=15+5*i;
			int kkk=(int)((kk/1.609)*1000);
			System.out.println(i+"    "+k/1000.0+"   |"+kk+"    "+kkk/1000.0);
		}
	}
}
