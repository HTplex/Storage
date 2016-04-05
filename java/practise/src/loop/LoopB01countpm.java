package loop;
import java.util.Scanner;
public class LoopB01countpm {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input a number(input 0 to end)");
		int i=input.nextInt();
		int sum=0;
		int average=0; 
		int countp=0;
		int countm=0;
		while (i!=0){
			if (i>0){
			countp++;
			sum+=i;
		}
			else{
				countm++;
				sum+=i;
				}
				i=input.nextInt();
			
				average=sum/(countm+countp);
		}
		System.out.println("the number of positives is "+countp);
		System.out.println("the number of negatives is "+countm);
		System.out.println("the average of the number is "+average);
		System.out.println("the sum of the numbers is "+sum);
	}
}
