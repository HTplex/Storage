package j14JavaFX;
import java.util.Scanner;
import java.math.BigInteger;
public class lab {
	public static void main(String[] args){
//		BigInteger a=BigInteger.ONE;
//		for(Integer i=2;i.compareTo(10000)<=0;i++){
//			//System.out.println(i.toString());
//			a=a.multiply(new java.math.BigInteger(i.toString()));
//		}
//		System.out.println(a.toString());
//		
		
		long e=2432902008176640000L;
		for(int i=2;i<=20;i++){
			while(test(e)&&e%i==0){
				e/=i;
			}
			e*=i;
		}
		System.out.println(e*2);
		for(int i=2;i<20;i++){
			System.out.println((e)/i);
		}
		//System.out.println(test(243290200817640000L));
	}
	public static boolean test(long e){
		//boolean b=true;
		for(int i=2;i<=20;i++){
			if(e%i!=0)
				return false;
		}
		return true;
	}
}
