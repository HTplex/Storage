package absinter;
import java.util.Scanner;
import java.math.*;
public class AbsA08big {
	public static void main(String[]args){
		BigInteger a=new BigInteger("1");
		for(int i=1;i<100;i++){
			a=a.multiply(new BigInteger(i+""));
		}
		System.out.println(a.toString());
		//BigDecimal c=new BigDecimal(a.toString());
		//c=c.divide(new BigDecimal(923850123987120359L+""),20,BigDecimal.ROUND_UP);
		//System.out.print(c.toString());
	}
}
