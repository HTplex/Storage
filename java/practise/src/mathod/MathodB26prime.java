package mathod;
import java.util.Scanner;
public class MathodB26prime {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please the number");
		int a=input.nextInt();
		System.out.println(prime(a));
	}
	public static long prime(int a){
		long output=2;
		long c=0;
		int div=2;
		for(int i=1;i<=a;i++){
			while (div<=(int)(Math.pow(output, 0.5))){
				if(output%div!=0){
			div++;}//if
				else{
				output++;
				div=2;
				}//else
			}//while
			c=output;
			output++;
			div=2;
			
			}

	return c;
	}
}	