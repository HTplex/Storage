package string;

import java.util.Scanner;
public class StringA19pirntwriter {
	public static void main(String[]args) throws Exception {
		Scanner input=new Scanner(System.in);/*cannnot(both open)*/
		input.close();
		java.io.File a=new java.io.File("D:/works/Javaworkspace/practise/files/javaStringA19printwritter.txt");
		if(a.exists()){
			a.delete();
			}
		java.io.File f=new java.io.File("D:/works/Javaworkspace/practise/files/javaStringA19printwritter.txt");
		java.io.PrintWriter p=new java.io.PrintWriter(f);
		for(int i=0;i<10;i++)
		p.println(i);
		for(int i=0;i<10;i++)
		p.println(i*Math.PI);
		for(int i=0;i<10;i++)
		p.println(System.currentTimeMillis()*1000000000+System.nanoTime());
		for(int i=0;i<10;i++)
		p.println(i%2==0);
		p.close();
		
		
	}
}
