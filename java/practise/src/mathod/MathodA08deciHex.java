package mathod;
import java.util.Scanner;
public class MathodA08deciHex {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input the number id deci");
		int a=input.nextInt();
		onenum(a);
	}
	public static void onenum(int x){
		String on="";
		while(x>0){
		on=change(x%16)+on;
		x/=16;
		}
		System.out.print(on);
	}
	public static char change(int i){
		char chan;
		if (i<10&&i>=0){
			chan =(char)(i+'0');
		}
		else chan=(char)(i-10+'a');
		return chan;
	}
}
