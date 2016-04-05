package array;
import java.util.Scanner;
public class ArrayB14gcdplus {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input 5 numbers");
		int [] numbers=new int [5];
		for(int i=0;i<5;i++)
		numbers[i]=input.nextInt();
		System.out.println(gcdplus(numbers[0],numbers[1],numbers[2],numbers[3],numbers[4]));
		input.close();
		
	}
	public static int gcd(int a,int b){
		for(int i=a;i>0;i--){
			if(a%i==0&b%i==0)
				return i;
		}
		return 1;
	}
	public static int gcdplus(int...number){
		int a=number[0];
		for(int i=1;i<number.length;i++){
			a=gcd(a,number[i]);
		}
		return a;
	}
}
