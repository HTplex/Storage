package error;
import java.util.Scanner;
public class ErrorA05circlepp {
	public static void main(String[]args){
		circlepp m=new circlepp();
		try{
			System.out.println("haha");
			m.setradius(-1);
			circlepp n=new circlepp(-1);
			circlepp w=new circlepp(3);
			System.out.println(w.r);
		
		}
		catch(IllegalArgumentException ex){
			System.out.println("r can not be negative");
			System.out.println(ex);
		}
	}
}
class circlepp{
	double r;
	circlepp(){
		this(1.0);
	}
	circlepp(double i)throws IllegalArgumentException{
		if (i>0)
		this.r=i;
		else
			throw new IllegalArgumentException("nonono");
	}
	public void setradius(double i)throws IllegalArgumentException{
		if (i>0)
			this.r=i;
		throw new IllegalArgumentException("r no -");
	}
}