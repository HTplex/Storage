package dp;
import java.util.Scanner;
public class Rods {
	public static void main(String[]args){
		Scanner in=new Scanner(System.in);
		 int n=in.nextInt();
		int[] p=new int[n];
		int[] c=new int[n];
		int length=-1;
		int price=-1;
		p[0]=0;
		for(int i=0;i<n;i++){
			p[i]=Integer.MIN_VALUE;
			c[i]=0;
		}
		do{
			length=in.nextInt();
			price=in.nextInt();
			p[length]=price;
		}while(length!=0);
		int i=in.nextInt();
		System.out.println(cut(i,p,c));
		for(int k=0;k<n;k++){
			System.out.print(c[k]+" ");
		}
		System.out.println();

		while(i>=0){
			if(c[i]==0){
				System.out.println(i);
				break;
			}
			System.out.println(c[i]);
			i-=c[i];
		}
		in.close();
	}
	public static int cut(int rod, int[] price, int[] c){
		if(price[rod]==0) return 0;
		if(price[rod]>0) return price[rod];
		int max=Integer.MIN_VALUE;
		if(price[rod]<0){
			for(int i=1;i<rod;i++){
				if(max<cut(i,price,c)+cut(rod-i,price,c)){
					max=cut(i,price,c)+cut(rod-i,price,c);
					c[rod]=i;
				}
			
			}
			//c[rod]=prt;
			price[rod]=max;
		}
		return max;
	}
	public static int max(int a, int b){
		return a>b?a:b;
	}
}
