package mathod;
import java.util.Scanner;
public class MathodA13char {
	public static char getchar(char a,char b){
		char out=(char)((Math.random()*((int)b-(int)a+1))+(int)a);
		return out;
	}
	public static char charA(){
		return getchar('A','Z'); 
	}
	public static char chara(){
		return getchar('a','z');
	}
	public static char char0(){
		return getchar('0','9');
	}
		public static char charx(){
			return getchar('\u0000','\uffff');
		}
		public static char charCHN(){
			return getchar('\u4e00','\u9fa5');
		}
		public static void main(String[]args){
			for (int i=1;i<100000;i++){
				System.out.print(charA());
				if (i%100==0)
					System.out.println();
			}
		}
}