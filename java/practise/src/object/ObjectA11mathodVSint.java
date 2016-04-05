package object;
import java.util.Scanner;
public class ObjectA11mathodVSint {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the radius of the circle");
		int r=input.nextInt();
		System.out.println("please input the number");
		int n=input.nextInt();
		circle3 c=new circle3(r);
		printn(c,n);
		System.out.println(c.getr());
	}
	static void printn(circle3 a, int n){
		for(int i=0;i<n;i++){
			System.out.println("the raidus is\t"+a.getr());
			System.out.println("the area is \t"+a.getarea());
			double rr=a.getr();
			rr--;
			a.changer(rr);
		}
	}
}

