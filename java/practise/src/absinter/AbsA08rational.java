package absinter;
import java.util.Scanner;
public class AbsA08rational {
	public static void main(String[]args){
	//	Rational a=new Rational(2L,6L);
	//	Rational b=new Rational(4L,12L);
	//	a.add(b);
	//	System.out.println(a.toString());
	}
}
abstract class Rational extends java.lang.Number implements java.lang.Comparable{
	private long numerator;
	private long denumerator;
	
	
	Rational(){
		this.numerator=0;
		this.denumerator=0;
	}
	Rational(long a, Long b){
		this.numerator=a/(this.getGCD(a,b));
		this.denumerator=b/(this.getGCD(a, b));
	}
	public int compareTo(Object b){
		this.minus((Rational) b);
		if(this.numerator/this.denumerator>0){
			return 1;
		}
		else if(this.numerator==0){
			return 0;
		}
		else return -1;
		}
	

	
	public void add(Rational b){
		this.numerator=this.numerator*b.denumerator+this.denumerator+b.numerator;
		this.denumerator*=b.denumerator;
		this.numerator/=getGCD(this.numerator,this.denumerator);
		this.denumerator/=getGCD(this.numerator,this.denumerator);
	}
	public void minus(Rational b){
		this.numerator=this.numerator*b.denumerator-this.denumerator+b.numerator;
		this.denumerator*=b.denumerator;
		this.numerator/=getGCD(this.numerator,this.denumerator);
		this.denumerator/=getGCD(this.numerator,this.denumerator);
	}
	public void multiply(Rational b){
		this.numerator=this.numerator*b.numerator;
		this.denumerator*=b.denumerator;
		this.numerator/=getGCD(this.numerator,this.denumerator);
		this.denumerator/=getGCD(this.numerator,this.denumerator);
	}
	public String toString(){
		String a=new String();
		String b=new String();
		a=String.valueOf(this.numerator);
		b=String.valueOf(this.denumerator);
		return(a+"/"+b);
	}
	public void divide(Rational b){
		this.numerator=this.numerator*b.denumerator;
		this.denumerator*=b.numerator;
		this.numerator/=getGCD(this.numerator,this.denumerator);
		this.denumerator/=getGCD(this.numerator,this.denumerator);
	}
	public long getNumerator(){
		return this.numerator;
	}
	public long getDenumrator(){
		return this.denumerator;
	}
	private long getGCD(long a,long b){
		long k=1;
		for(int i=1;i<a&&i<b;i++){
			if(i%a==0&&i%b==0){
				k=i;
			}
		}
		return k;
	}
	
}