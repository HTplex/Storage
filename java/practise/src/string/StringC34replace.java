package string;
import java.util.*;
import java.io.*;
public class StringC34replace {
	public static void main(String[]args)throws Exception{
		if(args.length!=3){
			System.out.println("usage: dir oldstring newstring");
			System.exit(0);
		}
		File f=new File(args[0]);
		if(!f.exists()){
			System.out.println("sorry the file does not exist");
			System.exit(0);
		}
		else{
			Scanner input=new Scanner(f);
			StringBuilder s=new StringBuilder();
			while(input.hasNextLine()){
				s.append(input.nextLine());
				s.append("\n");
			}
			input.close();
			String ss=new String(s.toString());
			ss=ss.replaceAll(args[1], args[2]);
			f.delete();
			File fr=new File(args[0]);
			PrintWriter pr=new PrintWriter(fr);
			pr.print(ss);
			pr.close();
		}
	}
}
