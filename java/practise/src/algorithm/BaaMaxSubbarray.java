package algorithm;
import java.util.Scanner;
public class BaaMaxSubbarray {
	public static void main(String[]args){
		int[] a={1,3,2,-1,-3,4,-6,4,-5};
		int[] b=findmaxarray(a,0,8);
		System.out.println(b[0]+" "+b[1]+" "+b[2]);
	}
	public static int[] findmax(int[] a,int l,int m,int h){
		int [] re=new int[3];
		int lowmax=-100000000;
		int sum=0;
		for(int i=m;i>=l;i--){
			sum+=a[i];
			if (lowmax<sum){
				lowmax=sum;
				re[0]=i;
			}
		}
		sum=0;
		int himax=-100000000;
		for(int i=h;i>m;i--){
			sum+=a[i];
			if(himax<sum){
				himax=sum;
				re[1]=i;
			}
		}
		re[2]=(lowmax+himax);
		return re;
	}
	public static int[] findmaxarray(int[] a,int l,int h){
		int [] re=new int[3];
		int[][] mm=new int[3][3];
		if(h==l){
			re[0]=l;
			re[1]=h;
			re[2]=a[l];
		}
		else{
			int m=(int)((l+h)/2);
			mm[0]=findmaxarray(a,l,m);
			mm[1]=findmaxarray(a,m+1,h);
			mm[2]=findmax(a,l,m,h);
		}
		if(mm[2][2]>mm[1][2]&&mm[2][2]>mm[0][2])
			return mm[2];
			else if(mm[1][2]>mm[0][2]&&mm[1][2]>mm[1][2])
				return mm[1];
				else return mm[0];
	}
}
