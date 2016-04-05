package absinter;
import java.util.Scanner;
import java.lang.*;
public class AbsA05cloneable {
	public static void main(String[]args){
		house a=new house();
		house b=new house();
		a.clone(b);
		System.out.println(a==b);
		System.out.println(a.equals(b));
	}
}
class house implements Cloneable,Comparable{
	private String shape;
	private int area;
	house(){
		this.shape="cirlce";
		this.area=1;
	}
	public int compareTo(Object a){
		if(this.area>((house)a).area){
			return 1;
		}
		else return -1;
	}
	public Object clone()throws CloneNotSupportedException{
		return super.clone();
	}
	public void clone(Object a){
		((house)a).area=this.area;
	}
}