package poj;
import java.util.Scanner;
public class Poj1002dial {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int n=input.nextInt();
		String[] s=new String[n];
		int[][] count=new int[100000][2];
		int[] sort=new int[n];
		
		for(int i=0;i<n;i++){
			s[i]=input.next();
			s[i]="1"+s[i];
			s[i]=min(s[i]);
			count[i][0]=tonumber(s[i]);

			for(int i1=0;i1<i;i1++){
				if ((count[i1][0]==count[i][0])&&(count[i][0]!=0)){
					count[i1][1]++;
					count[i][0]=0;
					
				}
			}
		}
		
		boolean b=false;
		int z=0;
		
		int[] outp=new int[count.length];
		for(int u=0;u<count.length;u++){
			count[u][1]++;
			if(count[u][1]>=2){
				z++;
				
				b=true;
			}
			}
		int[] t=new int[z];
		for(int u=0;u<z;u++){
			if(count[u][1]>=2){
			t[u]=10*count[u][0]+count[u][1];
			}
		}
		java.util.Arrays.sort(t);
		for(int i=0;i<z;i++){
			System.out.println(jiagang(t[i]));
		}
		
		if(!b){
			System.out.println("No duplicates.");
			System.exit(0);
		}
	}
	public static String min(String b){
		StringBuilder s=new StringBuilder();
		String sx;
		char[] c=b.toCharArray();
		for(int i=0;i<b.length();i++){
			if(c[i]!='-'){
				s.append(c[i]);
			}
		}
		sx=s.toString();
		return sx;
	}
	public static int tonumber(String s){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<s.length();i++){
			Character c=s.charAt(i);
			if(c.isUpperCase(c)){
				c=(char)((int)('0')+chartonumber(c));
			}
			sb.append(c);
		}
		String s1=sb.toString();
		int out=Integer.parseInt(s1);
		return out;
	}
	public static int chartonumber(char c){
		int out;
		int i=(int)(c);
		if(i<82){
			out=(int)((i-65)/3)+2;
		}
		else{
			out=(int)((i-66)/3)+2;
		}
		return out;
	}
	public static String jiagang(int i){
		String s=new String();
		s=s.valueOf(i);
		StringBuilder ss=new StringBuilder(s);
		ss.insert(7," ");
		ss.insert(4, '-');
		
		ss.deleteCharAt(0);
		s=ss.toString();
		return s;
	}
}
