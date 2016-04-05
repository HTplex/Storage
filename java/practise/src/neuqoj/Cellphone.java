package neuqoj;
import java.util.Scanner;
public class Cellphone {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=input.next();
		char[] c=s.toCharArray();
		for(int i=0;i<c.length;i++){
			switch (c[i]){
			case 'a' : System.out.print("a1");break;
			case 'b' : System.out.print("b2");break;
			case 'c' : System.out.print("c3");break;
			case 'd' : System.out.print("d1");break;
			case 'e' : System.out.print("e2");break;
			case 'f' : System.out.print("f3");break;
			case 'g' : System.out.print("g1");break;
			case 'h' : System.out.print("h2");break;
			case 'i' : System.out.print("i3");break;
			case 'j' : System.out.print("j1");break;
			case 'k' : System.out.print("k2");break;
			case 'l' : System.out.print("l3");break;
			case 'm' : System.out.print("m1");break;
			case 'n' : System.out.print("n2");break;
			case 'o' : System.out.print("o3");break;
			case 'p' : System.out.print("p1");break;
			case 'q' : System.out.print("q2");break;
			case 'r' : System.out.print("r3");break;
			case 's' : System.out.print("s4");break;
			case 't' : System.out.print("t1");break;
			case 'u' : System.out.print("u2");break;
			case 'v' : System.out.print("v3");break;
			case 'w' : System.out.print("w1");break;
			case 'x' : System.out.print("x2");break;
			case 'y' : System.out.print("y3");break;
			case 'z' : System.out.print("z4");break;
			}
		}
	}
}
