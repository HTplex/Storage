package mathod;
import java.util.Scanner;
public class MathodB30doubleprime {
	public static void main(String[]args){
		int a=2;
		int b=1;
		while(MathodB26prime.prime(a)<1000){
			while (a>b){
				if(MathodB26prime.prime(a)-MathodB26prime.prime(b)==2){
					System.out.println(MathodB26prime.prime(a)+"and"+MathodB26prime.prime(b));
				b++;
			}
					else b++;
			}
			a++;
			b=1;
		}
		System.out.println("that's it!");
	}
}
