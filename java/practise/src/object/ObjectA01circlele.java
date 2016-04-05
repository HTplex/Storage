package object;
import java.util.Scanner;
public class ObjectA01circlele {
	public static void main(String[]args){
		Circlele a=new Circlele();
		System.out.println("a:r="+a.r+"\ta:s="+a.area());
		Circlele c=new Circlele(5.0);
		for(int i=0;i<4;i++){
			c.r++;
			System.out.println("c:r="+c.r+"\tc:s="+c.area());
		}
	}
}
	class Circlele {
		double r;
		Circlele(){
			r=1.0;
		}
		Circlele(double newr){
			r=newr;
		}
		double area(){
			return r*r*Math.PI;
		}
	}

