package mathod;
import java.util.Scanner;
public class MathodB29dice {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int i=1;
		int sum=0;
		while(i==1){
			System.out.println("Welcome to dice,press 1=roll 0 to end");
			i=input.nextInt();
			int dice1=(int)(6*Math.random())+1;
			int dice2=(int)(6*Math.random())+1;
			sum=dice1+dice2;
			System.out.println("You rolled "+dice1+"+"+dice2+"="+sum);
			if (sum==3||sum==12){
				System.out.println("YOU LOSE!");
			System.out.println("Please input again");
			i=input.nextInt();
			}
			else if(sum==7||sum==11){
				System.out.println("YOU WIN!");
				break;
			}
			else {
				final int a=sum;
				int m=-1;
				int m2=-2;
				while(m!=m2){
					System.out.println("please input again");
					i=input.nextInt();
				dice1=(int)(6*Math.random())+1;
				dice2=(int)(6*Math.random())+1;
				m2=m;
				m=dice1+dice2;
				System.out.println("You rolled "+dice1+"+"+dice2+"="+m);
				if(m==7){
					System.out.println("YOU LOSE!");
					continue;
					
				}
				continue;
				}
				System.out.println("YOU WIN!");
			}
		}
	}
}
