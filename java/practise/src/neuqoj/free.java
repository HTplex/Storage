package neuqoj;
import java.util.Scanner;
public class free {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int l=input.nextInt();
		int m=input.nextInt();
		int t=0;
		for(int i=1;i<=l;i++){
			int sum=0;
			for(int i2=i;i2<=l;i2++){
				sum+=i2;
				if(sum==m)
					System.out.println("["+i+","+i2+"]");
			}
			
		}
	}
}