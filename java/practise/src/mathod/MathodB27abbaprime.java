package mathod;
import java.util.Scanner;
public class MathodB27abbaprime {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int i=1;
		int a=1;
		while(i<=100){
			int b=1;
			while(a>b){
				while(MathodB26prime.prime(a)!=MathodB26prime.prime(b)){
				b++;
				}
				System.out.printf("%6d",MathodB26prime.prime(a));
				i++;
				if(i%10==1)
					System.out.println();
			}
			a++;
		}
	}
}