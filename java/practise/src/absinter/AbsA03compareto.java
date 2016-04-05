package absinter;
import java.util.*;
public class AbsA03compareto {
	public static void main(String[]args){
		triii a=new triii(1);
		triii b=new triii(4);
		System.out.println(((triiicomp)a).compareTo(b));
	}
}
class triii extends Object{
	private int a;
	triii(){
	this(1);	
	}
	triii(int i){
		this.a=i;
	}
	public int geta(){
		return a;
	}
}
class triiicomp extends triii implements Comparable{
	public int compareTo(Object t){
		if(((triii)t).geta()>this.geta()){
			return 1;
		}
		else return -1;
	}
}