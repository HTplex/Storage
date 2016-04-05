package error;
import java.util.Scanner;
public class ErrorA08diyex {
	public static void main(String[]args){
		try{
		circleppp m=new circleppp(-1);
		}
		catch(Exception s){
			s.printStackTrace();
		}
	}
}
class circleppp{
	private double r;
	circleppp()throws Exception{
		this(1.0);
	}
	circleppp(double r)throws Exception{

			if(r>0)
				this.r=r;
			else
				throw new youridiot("DHR");
	}
}
class youridiot extends Exception{
	String s;
	youridiot(String s){
	super("you are a idiot: "+s);
	this.s=s;
	}
	public String getmes(){
		return s;
	}
}