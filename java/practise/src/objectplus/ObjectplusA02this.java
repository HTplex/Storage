package objectplus;
import java.util.Scanner;
public class ObjectplusA02this {
	public static void main(String[]args){
		circle a=new circle();
		System.out.println(a.getArea());
	}
}
class circle{
	double radius=5;
	public circle(double d){
		this.radius=d;
	}
	public circle(){
		this(1.0);
	}
	public double getArea(){
		double a=this.radius*this.radius*Math.PI;
		return a;
	}
}