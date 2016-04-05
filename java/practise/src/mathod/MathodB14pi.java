package mathod;
import java.util.Scanner;
public class MathodB14pi {
	public static void main(String[]args){
		System.out.println("i\t\tm(i)");
		for(int i=10;i<1000;i+=10){
			System.out.println(i+"\t\t"+m(i));
		}
	}
	public static double m(int i){
		double sum=0;
		for(int k=0;k<i;k++){
		double m=4*(Math.pow(-1,k)*(1.0/(2*k+1)));
		sum+=m;
		}
		return sum;
	}
}
