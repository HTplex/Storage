package neuqoj;
import java.util.Scanner;
public class Oj1096stamp {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=input.nextLine();
		String[] s2=s.split(" ");
		while(s.length()!=0){
		int l=0;
		int m=0;
		l=Integer.parseInt(s2[0]);
		m=Integer.parseInt(s2[1]);
		for(int i=1;i<=l;i++){
			int sum=0;
			for(int i2=i;i2<=l;i2++){
				sum+=i2;
				if(sum==m)
					System.out.println("["+i+","+i2+"]");
			}
		}
		s=input.nextLine();
		 s2=s.split(" ");
		}
	}
}
