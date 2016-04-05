package inhpoy;
import java.util.Scanner;
public class InhpoyA06instance {
	public static void main(String[]args){
		neyc s=new junior(15);
		if(s instanceof high){
			System.out.println(2*s.getnum());
		}
		if(s instanceof junior){
			System.out.println(99999999*s.getnum());
		}
	}
	public static void out(neyc c){
		System.out.println(c.getnum());
	}
}
class neyc{
	private int num;
	neyc(){};
	neyc(int c){
		this.num=c;
	}
	public void setnum(int num){
			this.num=num;
	}
	public int getnum(){
		return this.num;
	}
}
class high extends neyc{
	private int sum=super.getnum()*5;
	high(int c){
		super.setnum(c);
	}
	
	

}
class junior extends neyc{
	private int sum=super.getnum()*10000;
	junior(int c){
		super.setnum(c);
	}
	public int getsum(){
		return sum;
	}
}