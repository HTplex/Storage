package string;
import java.util.Scanner;
public class StringC01ssd {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String ssd=new String(input.next());
		char[] c=ssd.toCharArray();
		boolean b=true;
		if(c.length!=11)
			b=false;
		for(int i=0;i<3;i++){
		Character cc=new Character(c[i]);
		if(!cc.isDigit(cc)) b=false;
		}
		if(ssd.charAt(3)!='-'||ssd.charAt(6)!='-')
			b=false;
		for(int i=4;i<6;i++){
			Character cc=new Character(c[i]);
			if(!cc.isDigit(cc)) b=false;
			}
		for(int i=7;i<11;i++){
			Character cc=new Character(c[i]);
			if(!cc.isDigit(cc)) b=false;
			}
		System.out.println(b);
	}
}
