package freepritice;
import java.util.Scanner;
public class Normal01divideprime {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
	long i=input.nextLong();
	StringBuilder a=new StringBuilder();
	long e=0;
	long i2=2;
	while(i>=i2){
		while(i%i2==0){
			a.append(i2+"*");
			e++;
			i/=i2;
		}
		i2++;
	}
	a.deleteCharAt(a.lastIndexOf("*"));
	System.out.println(a);
	}
}
