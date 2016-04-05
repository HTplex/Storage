package object;
import java.util.Scanner;
public class ObjectB04random {
	public static void main(String[]args){
		java.util.Random a=new java.util.Random(1000);
		for(int i=0;i<50;i++){
			System.out.println(a.nextInt(100));
		}
	}
}
