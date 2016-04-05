package choose;
import java.util.Scanner;
public class ChooseAequiz {
	public static void main(String[]args){
		int numone=(int)(Math.random()*10);
		int numtwo=(int)(Math.random()*10);
		if (numone>numtwo){
				int x=numone;
				numone=numtwo;
				numtwo=x;
		}
		System.out.print("What is "+numtwo+"-"+numone+" ");
		Scanner input=new Scanner(System.in);
		int ans=input.nextInt();
		if(numtwo-numone==ans){
			System.out.println("you are correct !");
		}
		else System.out.println("your answer is wrong\nthe correct answer of "+
		numtwo+"-"+numtwo+" is "+(numtwo-numone));
		
		}
	}
