package absinter;
import java.util.Scanner;
public class AbsA02interface {
	public static void main(String[]args){
		chicken c=new chicken();
		if(c instanceof ediable){
			System.out.println(c.getQuuk()); 
			
		}
	}
}
interface ediable{
	public abstract String how();
	public abstract boolean edable();
}
class food{
	private int weight;
	private int height;

	food(int w,int h){
		this.height=h;
		this.weight=w;
	}
	food(){
		this(1,1);
	}
}
class chicken extends food implements ediable{
	private String quuk;
	public String getQuuk(){
		return this.quuk;
	}
	public String how(){
		return "fried";
	}
	public boolean edable(){
		return true;
	}
	chicken(){
		this.quuk="quuk";
	}
}