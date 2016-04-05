package string;
import java.util.Scanner;
import java.io.*;

import javax.swing.JFileChooser;
public class StringC33cutterplus {
	public static void main(String[]args)throws Exception{
	JFileChooser f=new JFileChooser();
	Scanner input=new Scanner(System.in);
	if(f.showOpenDialog(null)==f.APPROVE_OPTION){
		File c=f.getSelectedFile();
		Scanner read=new Scanner(c);
		int im=0;
		while(read.hasNext()){
			String s=read.next();
			im++;
		}
		read.close();
		String[] s=new String[im];
		Scanner readagain=new Scanner(c);
		int ii=0;
		while(readagain.hasNext()){
			s[ii]=readagain.next();
			ii++;
		}
		readagain.close();
		//for(String cc:s){
		//System.out.println(cc);
		
	
	
	
	int i=(int)(s.length*Math.random());
	StringBuilder test=new StringBuilder(s[i]);
	String tests=test.toString();
	StringBuilder testx=new StringBuilder(s[i]);
	String testxs=test.toString();
	StringBuilder outs=new StringBuilder(tostar(s[i]));
	String out=outs.toString();
	StringBuilder acc=new StringBuilder("");
	String accs=acc.toString();
	int charat;
	String in=new String();
	int time=10;
	
	while(true){
		if(tests.equals(out)){
			System.out.println("\nCONGRATULATIONS! you still have "+time+" times left");
			System.exit(0);
		}
		
		System.out.print("enter a letter    "+out);
		System.out.println("                        (already input):  "+accs);
		System.out.println("you have "+time+" times left");
		System.out.print(">>>");
		in=input.next();
		System.out.print("\n\n");
		
		if(time==1){
			System.out.println("sorry you have no chances\n the word is "+tests);
			System.exit(0);
		}
		
		if(in.length()>1){
			System.out.println("you can only input one number");
			in=input.next();
		}
		
		if(!accs.contains(in)){
			
			acc.append(in);
			accs=acc.toString();
			
			if(!tests.contains(in)) {
				time--;
				System.out.println("sorry the letter is not in the word");
			//System.out.print("enter a word "+out+" >>");
			}
			
			while(testxs.contains(in)){
				outs.replace(findnumber(in,testxs),findnumber(in,testxs)+1,in);
				out=outs.toString();
				testx.replace(findnumber(in,testxs),findnumber(in,testxs)+1,"*");
				testxs=testx.toString();
			}
			
			
		}
		else {
			acc.append(in);
		accs=acc.toString();
		if(tests.contains(in)){
			System.out.println("the letter "+in+" is already exist~");
			//System.out.print("enter a word "+out+" >>");
		}
		else {
			System.out.println("the letter "+in+" is already input");
			//System.out.print("enter a word "+out+" >>");
		}
		}
		}
	}
	
}
public static int findnumber(String a, String b){
	char[] aa=a.toCharArray();
	int ii=0;
	for(int i=0;i<b.length();i++){
		if(aa[0]==b.charAt(i)){
		ii=i;
		}
	}
	return ii;
}
public static String tostar(String s){
	for(int i=0;i<s.length();i++)
	s=s.replace(s.charAt(i),'*');
	return s;
}
}

