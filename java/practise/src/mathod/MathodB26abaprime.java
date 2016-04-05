package mathod;
import java.util.Scanner;
public class MathodB26abaprime {
	public static void main(String[]args){
		int i=1;
		int line=0;
		int a=1;
		while(i<100){
			if(MathodB26prime.prime(a)==MathodB04reverse2.reverse(MathodB26prime.prime(a))){
				System.out.printf("%9d",MathodB26prime.prime(a));
				a++;
				line++;
				i++;
				if (line%10==0)
					System.out.println();
			}
			else a++;
		}
		
	}
}
