package string;
import java.util.Scanner;
public class StringC09BtoH {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=input.next();
		while((int)(s.length())%4!=0){
		s='0'+s;
		}
		//StringBuilder ss=new StringBuilder(s);
		//ss.reverse();
		//s=ss.toString();
		//System.out.println(s);
		String[] sub=new String[s.length()/4];
		for(int i=0;i<s.length()/4;i++){
			sub[i]=s.substring(4*i,4*i+4);
			int ii=btod(sub[i]);
			System.out.print(dtohex(ii));
		}
		
	//	for(String i:sub)
	//		System.out.println(i);
	}
	public static int btod(String a){
		int sum=0;
		for(int i=0;i<a.length();i++){
			if(a.charAt(i)=='1'){
				sum++;
			}
			sum*=2;
		}
		return sum/2;
	}
	public static char dtohex(int a){
		if(0<=a&&a<10)
			return((char)('0'+a));
		else return (char)('A'-10+a);
	}
}
