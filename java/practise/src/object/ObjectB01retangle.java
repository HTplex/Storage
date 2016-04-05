package object;
import java.util.Scanner;
public class ObjectB01retangle {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("long edge:");
		double l=input.nextDouble();
		System.out.println("short edge:");
		double s=input.nextDouble();
		retangle a=new retangle(l,s);
		System.out.println(a.showarea());
		System.out.println(a.showcircumference());
	}
}
	class retangle{
		double l;
		double s;
		retangle(){
			l=0;
			s=0;
		}
		retangle(double a,double b){
			l=a;
			s=b;
		}
		double showarea(){
			return l*s; 
		}
		double showcircumference(){
			return 2*(l+s);
		}
	}

