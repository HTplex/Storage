package absinter;
import java.util.Scanner;
public class AbsA01abstract {
	public static void main(String[]args){
		geo2[] a=new geo2[10];
		for(int i=0;i<5;i++){
			a[i]=new trio(i,i,i);
		}
		for(int i=5;i<10;i++){
			a[i]=new quar(i,i);
		}
		for(int i=0;i<5;i++){
			if(a[i].getArea()<a[i+5].getArea());
			System.out.println("trio is bigger");
		}
		
	}
}
abstract class geo2{
	private String color;
	private int n;
	geo2(){
		this(1);
	}
	geo2(int i){
		this.n=i;
	}
	public abstract double getArea();

}
class trio extends geo2{
	private int a;
	private int b;
	private int c;
	trio(int a, int b, int c){
		this.a=a;
		this.b=b;
		this.c=c;
	}
	trio(){
		this(1,1,1);
	}
	public double getArea(){
		return this.a+this.b+this.c;
	}
}
class quar extends geo2{
	private int a;
	private int b;
	quar(){
		
	}
	quar(int a, int b){
		this.a=a;
		this.b=b;
	}
	public double getArea(){
		return this.a*this.b;
	}
}