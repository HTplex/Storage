package string;
import java.util.Scanner;
public class StringA03mathod {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int i=input.nextInt();
		String[] s=new String[10];
		s[0]=new String("hello");
		s[1]=new String("Hello");
		s[2]=new String("Helloi");
		s[4]=new String("helloi");
		switch (i){
		case 1:System.out.println(s[0]+"\n"+s[1]+"\n"+s[0].equals(s[1])+"\n");
			   System.out.println(s[0]+"\n"+s[0]+"\n"+s[0].equals(s[0]));break;
		case 2:System.out.println(s[0]+"\n"+s[1]+"\n"+s[0].equalsIgnoreCase(s[1]));break;
		case 3:System.out.println(s[0]+"\n"+s[2]+"\n"+s[0].compareTo(s[2])+"\n");
			   System.out.println(s[0]+"\n"+s[1]+"\n"+s[0].compareTo(s[1])+"\n");
			   System.out.println(s[1]+"\n"+s[2]+"\n"+s[1].compareTo(s[2])+"\n");
		case 4:System.out.println(s[0]+"\n"+s[2]+"\n"+s[0].compareToIgnoreCase(s[2])+"\n");
			   System.out.println(s[0]+"\n"+s[1]+"\n"+s[0].compareToIgnoreCase(s[1])+"\n");
			   System.out.println(s[1]+"\n"+s[2]+"\n"+s[1].compareToIgnoreCase(s[2])+"\n");break; 
		case 5:System.out.println(s[0]+"\n"+s[1]+"\n"+s[0].regionMatches(1,s[1],1,3));break;
		case 6:System.out.println(s[0]+"\n"+s[2]+"\n"+s[0].regionMatches(true,0,s[2],0,4));break;
		case 7:System.out.println(s[0]+"\n"+s[0].startsWith("hel"));break;
		case 8:System.out.println(s[0]+"\n"+s[0].endsWith("lo"));break;
		
		}

	}
	
}
