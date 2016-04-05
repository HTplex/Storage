package string;

import java.util.Scanner;
public class StringB15filereview {
	public static void main(String[]args) throws Exception {
		java.io.File a=new java.io.File("D:/works/Javaworkspace/practise/files/test.txt");
		//Scanner input=new Scanner(a);input cant coexist with output
		if(a.exists()){
			System.out.println("hehe");
			a.delete();
			System.exit(0);
		}
		java.io.File b=new java.io.File("D:/works/Javaworkspace/practise/files/test.txt");
			java.io.PrintWriter p=new java.io.PrintWriter(b);
			for(int i=0;i<1000000;i++){
				p.print(i+" ");
				if(i%100==0)
				p.println("\n");
			}
			p.close();
	}
}
