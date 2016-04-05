package inhpoy;
import java.util.Scanner;
public class InhpoyA07equals {
	public static void main(String[]args){
		System.out.println(circle2.equals(new circle2(1),new circle2(1)));
		System.out.println(circle2.equals(new circle2(2),new circle2(1)));
	}
}
class circle2{
	private int r;
	circle2(){
		this(1);
	}
	circle2(int i){
		this.r=i;
	}
	public static boolean equals(circle2 a, circle2 b){
		return a.r==b.r;
	}
}