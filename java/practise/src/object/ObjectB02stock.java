package object;
import java.util.Scanner;
public class ObjectB02stock {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("input previous");
		int p=input.nextInt();
		System.out.println("input after");
		int f=input.nextInt();
		stock a=new stock(p,f);
	System.out.println(a.getChangePercent());
	}
}
	class stock{
		String symbol;
		String name;
		double previous;
		double recent;
		stock(double a,double b){
			symbol="FB";
			name="Facebook";
			previous=a;
			recent=b;
		}
		double getChangePercent(){
			return (recent/previous-1);
		}
	}

