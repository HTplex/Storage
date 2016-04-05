package object;
import java.util.Scanner;
public class ObjectB03date {
	public static void main(String[]args){
		java.util.Date[] a=new java.util.Date[8];
		for(int i=0;i<8;i++){
			a[i]=new java.util.Date((int)(Math.pow(10, i+4)));
		}
		for(int i=0;i<8;i++){
			System.out.println(a[i].toString());
		}
	}
}
