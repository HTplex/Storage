package error;
import java.util.*;
public class ErrorB02self {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		boolean bb=true;
		do{
			try{
			int a=input.nextInt();
			int b=input.nextInt();
			int c=input.nextInt();
			tri m=new tri(a,b,c);
			bb=false;
			}
			catch(Exception e){
				System.out.println("hey hey!that is not even a triagonal");
				input.nextLine();
			}
		}while(bb);
	}
}
class tri{
	private int a;
	private int b;
	private int c;
	tri(){
		
	}
	tri(int a,int b,int c)throws Exception{
		
			if(a+b>c&&b+c>a&&a>b){
			this.a=a;
			this.b=b;
			this.c=c;
			}
			else{
				throw new shitexception("hehe");
			}
	}
}
class shitexception extends Exception{
	String s;
	shitexception(String s){
		super(s);
		this.s=s;
	}
	public String toString(){
		return s;
	}
}