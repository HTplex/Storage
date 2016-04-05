package string;
import java.util.Scanner;
public class StringA06edit {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int i=-1;
		while (i!=0){
		String s=new String("   This is a long long Test String for this Class  ");
		System.out.println("\n["+s+"]");
		i=input.nextInt();
		switch (i){
		case 1:System.out.println(s.toLowerCase());break;
		case 2:System.out.println(s.toUpperCase());break;
		case 3:System.out.println(s.trim());break;
		case 4:System.out.println(s.replace('s','v'));break;
		case 5:System.out.println(s.replaceFirst("long","shit"));break;
		case 6:System.out.println(s.replaceAll("long","shit"));break;
		case 7:{
			String[] tokens=s.split(" ");
			for(String m:tokens)
				System.out.println(m);
		}
		}
		int ss=input.nextInt();
		}
	}
}
