package string;
import java.util.Scanner;
public class StringC29splitplus {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=input.next();
		String[] a=splitplus(s,"@");
		for(String i:a){
			System.out.println(i);
		}
	}
	public static String[] splitplus(String a, String b){
		StringBuilder s1=new StringBuilder(a);
		String[] q=new String[a.length()];
		for(int i=0;i<q.length;i++){
			q[i]="0";
		}
		int c=0;
		int cp=0;
		int i;
		for(i=0;i<a.length()-b.length()+1;i++){
			String sub=a.substring(i,i+b.length());
			if(sub.equals(b)){
				q[c]=a.substring(cp,i-b.length()+1);
				c++;
				q[c]=a.substring(i-b.length()+1,i+1);
				c++;
				cp=i+1;
			}
		}
		q[c]=a.substring(cp,i-b.length()+1);
		return q;
	}
}
