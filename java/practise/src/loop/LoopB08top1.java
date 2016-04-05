package loop;
import java.util.Scanner;
public class LoopB08top1 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number of students");
		int count=input.nextInt();
		int i=0;
		String b="";
		int m=0;
		while (count>0){
			System.out.println("please input the name of the student");
			String a=input.next();
			System.out.println("pleasse input the score of the student");
			i=input.nextInt();
			if (i>=m){
				b=a;
				m=i;
			}
				count--;
		}
		System.out.println("the highest scored student is "+b);
	}
}
