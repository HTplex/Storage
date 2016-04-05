package string;
import java.util.Scanner;
import java.io.*;
public class StringA21replace {
	public static void main(String[]args) throws Exception{
		if(args.length!=4){
			System.out.println("synatic error");
			System.exit(0);
		}
		File startfile=new File(args[0]);
		if(!startfile.exists()){
			System.out.println("this file does not exist");
		System.exit(0);
		}
		File endfile=new File(args[1]);
		if(endfile.exists()){
			System.out.println("this file already exist");
		System.exit(0);
		}
		PrintWriter comeout=new PrintWriter(endfile);
		Scanner input=new Scanner(startfile);
		while(input.hasNextLine()){
			String s=input.nextLine();
			String ss=s.replace(args[2], args[3]);
			comeout.print(ss);
		}
		input.close();
		comeout.close();
	}
}
