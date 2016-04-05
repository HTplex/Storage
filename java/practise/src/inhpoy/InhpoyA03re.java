package inhpoy;
import java.util.Scanner;
public class InhpoyA03re {
	public static void main(String[]args){
		sun a=new sun();
		a.r(2);
		a.r(2.0);
	}
}
class father{
	public void r(double a){
		System.out.println(2*a);
	}
}
class sun extends father{
	//public void r(double a){
	//	System.out.println(a);
	//}
	public void r(int a){
		System.out.println(a);
	}
}
