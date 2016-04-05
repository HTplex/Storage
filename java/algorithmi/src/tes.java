
import java.math.BigDecimal;
import java.util.Scanner;
public class tes {
	public static void main(String[]args){
		Scanner in=new Scanner(System.in);
		BigDecimal a=new BigDecimal(in.next());
		int b=in.nextInt();
		BigDecimal m=a.pow(b);
		
		System.out.println(m);
		in.close();
	}
}