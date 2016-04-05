package loop;
import java.util.Scanner;
public class LoopB09top2 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number of the students");
		int number=input.nextInt();
		int top1=0;
		int top2=0;
		String top1n="";
		String top2n="";
		while (number>0){
			System.out.println("please input the name of the student");
			String n=input.next();
			System.out.print("please input the score of the student");
			int score=input.nextInt();
			if (score>top1){
				top2=top1;
				top2n=top1n;
				top1=score;
				top1n=n;
			}
			else if(score>top2){
				top2=score;
				top2n=n;
				}
				number--;
		}
			System.out.println("the student with the highest score is"+top1n+"\nthe student with the second top score is"+top2n);
	}
}
