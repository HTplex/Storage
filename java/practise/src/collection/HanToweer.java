package collection;
import java.util.Scanner;
public class HanToweer {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int i=input.nextInt();
		movea(i,1,3);
	}
	public static void moveeven(int i, int i1){
		int b=6-i-i1;
		//if(c==2){
			System.out.print(i+"->"+b+" ");
			System.out.print(i+"->"+i1+" ");
			System.out.print(b+"->"+i1+" ");
			System.out.println();
		//}
		//else(movea(i,i1));
	}
	public static void movea(int c,int i,int i2){
		
		int b=6-i-i2;
		if(c==2) moveeven(i,i2);
		if(c!=2){
			movea(c-1,i,b);
			System.out.print(i+"->"+i2+" ");
			movea(c-1,b,i2);
		}
	}
}
