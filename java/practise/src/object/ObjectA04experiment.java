package object;
import java.util.Scanner;
public class ObjectA04experiment {
	public static void main(String[]args){
		circle a=new circle(5);
		circle b=a;
		a.r=4;
		System.out.println(b.r);
		a.r++;
		System.out.println(b.r);
		
	}
}
	class circle{
		double area;
		double r;
		circle(){
		area=0;
		}
		circle(double newr){
			r=newr;
		}
	}

