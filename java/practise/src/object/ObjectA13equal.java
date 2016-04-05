package object;
import java.util.Scanner;
public class ObjectA13equal {
	public static void main(String[]args){
		circle a=new circle(1);
		circle b=new circle(2);
		circle temp;
		show1(a,b);
		System.out.println(a.r+" "+b.r);
		a=new circle(1);
		b=new circle(2);
		show2(a,b);
		System.out.println(a.r+" "+b.r);
	}
	public static void show1(circle a,circle b){
		circle temp=a;
		a=b;
		b=temp;
	}
	public static void show2(circle a,circle b){
		double temp=a.r;
		a.r=b.r;
		b.r=temp;
	}
	
}
