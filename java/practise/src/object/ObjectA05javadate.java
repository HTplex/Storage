package object;
import java.util.Scanner;
public class ObjectA05javadate {
	public static void main(String[]args){
		java.util.Date a=new java.util.Date();
		java.util.Date b=new java.util.Date(100000000000L);
		System.out.println("toString\n"+a.toString());
		System.out.println("gettime\n"+a.getTime());
		System.out.println("toString\n"+b.toString());
		System.out.println("gettime\n"+b.getTime());
		a.setTime(100000000L);
		System.out.println("toString\n"+a.toString());
		System.out.println("gettime\n"+a.getTime());
	}
}
