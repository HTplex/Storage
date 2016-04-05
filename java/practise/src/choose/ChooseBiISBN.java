package choose;
import java.util.Scanner;
public class ChooseBiISBN {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		byte d1=input.nextByte();
		byte d2=input.nextByte();
		byte d3=input.nextByte();
		byte d4=input.nextByte();
		byte d5=input.nextByte();
		byte d6=input.nextByte();
		byte d7=input.nextByte();
		byte d8=input.nextByte();
		byte d9=input.nextByte();
		int d10=(d1+2*d2+3*d3+4*d4+5*d5+6*d6+7*d7+8*d8+9*d9)%11;
		if (d10==10) System.out.println(d1+""+d2+""+d3+""+d4+""+d5+""+d6+""+d7+""+d8+""+d9+""+d10+"X");
		else System.out.println(d1+""+d2+""+d3+""+d4+""+d5+""+d6+""+d7+""+d8+""+d9+""+d10);
	}
}
