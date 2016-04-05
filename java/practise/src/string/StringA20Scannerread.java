package string;
import java.util.Scanner;
public class StringA20Scannerread {
	public static void main(String[]args) throws Exception{
		java.io.File f=new java.io.File("D:/works/Javaworkspace/practise/files/javaStringA19printwritter.txt");
		Scanner s=new Scanner(f);
		//s.useDelimiter("[s,0]");
		while(s.hasNext()){
		String a=s.nextLine();
		//int b=s.nextInt();
		System.out.println(a);
		}
		s.close();
	}
}
