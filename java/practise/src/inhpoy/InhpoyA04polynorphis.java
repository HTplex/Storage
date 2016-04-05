package inhpoy;
import java.util.Scanner;
public class InhpoyA04polynorphis {
	public static void main(String[]args){
		paint(new cir(1.0));
	}
	 static void paint(Geo g){
		System.out.println("color is "+g.getcolor());
	}
}
class  Geo{
	private String color;

	Geo(){
		this.color="blue";
	}
	Geo(String color){
		this.color=color;
	}
	public String getcolor(){
		return this.color;
	}
	public void setcolor(String c){
		this.color=c;
	}

	public String getinfo(){
		String s=("color: "+this.color);
		return s;
	}
}
class cir extends Geo{
	private double radius;
	private static final double pi=Math.PI;
	
	cir(){
		this(1.0);
	}
	cir(double d){
		this.radius=d;
	}
	cir(double radius,String color,boolean fill){
		this.radius=radius;
		setcolor(color);
	}
}
