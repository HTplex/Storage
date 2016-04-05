package loop;
import java.util.Scanner;
public class LoopB37bin {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number you want");
		long put=input.nextLong();
		long k=(long)(Math.pow(2, 62));
		while (k>put){
			k/=2;
		}
		for(long i=k;i>0;i/=2){
			if(i>put)
				System.out.print("0");
				else {
					System.out.print("1");
					put-=i;
				}
			}
		}
	}

