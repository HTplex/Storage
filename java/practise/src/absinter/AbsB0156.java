package absinter;
import java.util.Scanner;
import java.math.*;
public class AbsB0156 {
	public static void main(String[]args){
		BigInteger a=new BigInteger("1");
		for(int i=0;i<256;i++){
			a=a.add(a);
			System.out.println(a.toString());
		}
		
	}
}
