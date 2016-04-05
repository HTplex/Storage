package object;
import java.util.Scanner;
public class ObjectA08static {
	public static void main(String[]args){
		circle22 a=new circle22();
		circle22 b=new circle22(5);
		System.out.println(a.publiccircle);
		a.publiccircle=100;
		System.out.println(b.publiccircle);
	}
}
