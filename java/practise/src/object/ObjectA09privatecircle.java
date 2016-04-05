package object;
import java.util.Scanner;
public class ObjectA09privatecircle {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		circle3 a=new circle3();
		circle3 b=new circle3(4);
		System.out.println("r="+b.getr());
		System.out.println("area="+b.getarea());
		System.out.println("enter the password to change the r");
		int pass=input.nextInt();
		if(pass==174216){
			System.out.println("passed! please input new r");
			double rrr=input.nextDouble();
			b.changer(rrr);
		}
		else System.out.println("wrong password");
		System.out.println("the new r is"+b.getr());
		System.out.println("the new area is"+b.getarea());
	}
}
 class circle3{
		private static int number=0;
		private double r;
		circle3(){
			r=1.0;
		}
		public circle3(double rr){
			r=rr;
			number++;
		}
		public double getarea(){
			return r*r*Math.PI;
		}
		public void changer(double r1){
			r=r1;
			number++;
		}
		public static int getnumber(){
			return number;
		}
		public double getr(){
		return r;
		}
	}

