package mathod;
import java.util.Scanner;
public class MathodB05order {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the 3 numbers you want to order");
		int a=input.nextInt();
		int b=input.nextInt();
		int c=input.nextInt();
		order(a,b,c);
	}
	public static void order(int a, int b, int c){
		int cache;
		if (a<b){
			cache=a;
			a=b;
			b=cache;
			if(c>a){
				cache=c;
				c=b;
				b=a;
				a=cache;
			}
				else if(c>b){
					cache=b;
					b=c;
					c=cache;
				}
				
			}
		else if(c>b){
			cache=b;
			b=c;
			c=cache;
		}
		System.out.println(a+" "+b+" "+c);
	}
}
