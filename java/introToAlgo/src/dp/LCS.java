package dp;
import java.util.Scanner;
public class LCS {
	public static void main(String[]args){
		Scanner in=new Scanner(System.in);
		String s1=in.next();
		String s2=in.next();
		String[][] sub=new String[s1.length()][s2.length()];
		int[][] n=new int[s1.length()][s2.length()];
		for(int i=0;i<n.length;i++)
			for(int i1=0;i1<n[0].length;i1++){
				n[i][i1]=Integer.MIN_VALUE;
				sub[i][i1]="";
				if(i==0){
					n[i][i1]=0;
					sub[i][i1]=s2.substring(i1,i1+1);
				}
				if(i1==0){
					n[i][i1]=0;
					sub[i][i1]=s1.substring(i,i+1);
				}
			}
				System.out.println(lcs(n,sub,s1,s2));
				for(String[] k:sub){
					for(String k1:k)
						System.out.print(k1+"\t\t");
					System.out.println();
				}
				in.close();
	}
	public static int lcs(int[][] n,String[][] sub, String s1, String s2){
		int l1=s1.length()-1;
		int l2=s2.length()-1;
		if(n[l1][l2]>=0)
			return n[l1][l2];
		if(s1.charAt(s1.length()-1)==s2.charAt(s2.length()-1)){
			n[l1][l2]=lcs(n,sub,s1.substring(0, l1),s2.substring(0, l2))+1;
			sub[l1][l2]=sub[l1-1][l2-1]+s1.charAt(s1.length()-1);
			return n[l1-1][l2-1]+1;
		}
		else{
			if(lcs(n,sub,s1.substring(0, l1),s2)>lcs(n,sub,s1,s2.substring(0, l2))){
				n[l1][l2]=lcs(n,sub,s1.substring(0, l1),s2);
				sub[l1][l2]=sub[l1-1][l2];
			}
			
			//n[l1][l2]=max(lcs(n,sub,s1.substring(0, l1),s2),lcs(n,sub,s1,s2.substring(0, l2)));
			else{
				n[l1][l2]=lcs(n,sub,s1,s2.substring(0, l2));
				sub[l1][l2]=sub[l1][l2-1];
			}
			return n[l1][l2];
		}
	}
	public static int max(int a, int b){
		return a>b?a:b;
	}
}
