package mathod;
import java.util.Scanner;
public class MathodB28masonprime {
	public static void main(String[]args){
		System.out.println("     p     2^p-1");
		int a=1;
		int i=1;
		while(i<=23){
			a=1;
			while(MathodB26prime.prime(a)<(Math.pow(2, i)-1)){
			a++;
			}
			if(MathodB26prime.prime(a)==Math.pow(2, i)-1){
				System.out.printf("%6d%6d",i,(int)(Math.pow(2, i)-1));
				System.out.println();
			}
				i++;

	}
	}		
}
