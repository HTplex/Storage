package inhpoy;
import java.util.Scanner;
public class InhpoyA01geocir {
	public static void main(String[]args){
		circle s=new circle();
		s.setcolor("blue");
		System.out.println(s.getcolor());
	}
}
class Geometric{
	private String color;
	private boolean filled;
	private java.util.Date datecreated;
	
	Geometric(){
		this.datecreated=new java.util.Date();
	}
	Geometric(String color,boolean fill){
		this.datecreated=new java.util.Date();
		this.color=color;
		this.filled=fill;
	}
	public String getcolor(){
		return this.color;
	}
	public void setcolor(String c){
		this.color=c;
	}
	public boolean isFilled(){
		return this.filled;
	}
	public void setfilled(boolean b){
		this.filled=b;
	}
	public java.util.Date getdate(){
		return this.datecreated;
	}
	public String getinfo(){
		String s=("color: "+this.color+"\nfilled: "+this.filled+"\ndate: "+this.datecreated);
		return s;
	}
}
class circle extends Geometric{
	private double radius;
	private static final double pi=Math.PI;
	
	circle(){
		this(1.0);
	}
	circle(double d){
		this.radius=d;
	}
	circle(double radius,String color,boolean fill){
		this.radius=radius;
		setcolor(color);
		setfilled(fill);
	}
	
}