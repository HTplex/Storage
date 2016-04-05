package object;
import java.util.Scanner;
public class ObjectA10mathod {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		circle3 a=new circle3();
		for(int i=0;i<3;i++){
			System.out.println("input password");
			int b=input.nextInt();
			if(b==174216){
				System.out.println("passed!\nplease input new r");
				double newr=input.nextDouble();
				a.changer(newr);
			}
			print(a);
		}
	}
	static void print(circle3 a){
		System.out.println("the radius of the circle is\t"+a.getr());
		System.out.println("the area of the circle is\t"+a.getarea());
	}
}
