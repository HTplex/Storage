package string;
import java.util.Scanner;
public class StringC31cutter {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String[] base=new String[10];
		base[0]="you";
		base[1]="interview";
		base[2]="circle";
		base[3]="loop";
		base[4]="kitty";
		base[5]="english";
		base[6]="usa";				
		base[7]="international";
		base[8]="bike";
		base[9]="tank";

		//int i=1;
		int i=(int)(base.length*Math.random());
		StringBuilder test=new StringBuilder(base[i]);
		String tests=test.toString();
		StringBuilder testx=new StringBuilder(base[i]);
		String testxs=test.toString();
		StringBuilder outs=new StringBuilder(tostar(base[i]));
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
