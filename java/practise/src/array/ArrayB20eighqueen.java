package array;
import java.util.Scanner;
public class ArrayB20eighqueen{
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int [] position=new int [8];
		position [0]=0;
		boolean j=true;
		int i=1;
		int k=1;
		while(k<7){
		while(i<64){
			j=true;
			for(int m=0;m<k;m++)
		 j=j&&kill(position[m],i);
			
		if(j){
			position[k]=i;
			k++;
		}
		i++;
		}
	k--;
	i=position[k]+1;
		}
		for(int r:position)
			System.out.println(r);
		print(position);
		
			
		
	}
	public static boolean kill(int a,int b){
		boolean k=true;
		if(a%8==b%8)
			k=false;
		if(((int)(a/8))==((int)(b/8)))
			k=false;
		if(((int)(a/8)-(int)(b/8))==(a%8-b%8))
			k=false;
		if(((int)(a/8)-(int)(b/8))==(b%8-a%8))
		k=false;
			if(a==-1)
				k=true;
			return k;
	}
	public static void print(int[] a){
		char [] count=new char [64];
		for(int i=0;i<a.length;i++){
			count[a[i]]='Q';
		}
		for(int i=0;i<count.length;i++){
			System.out.print("|"+count[i]);
			if(i%8==7)
				System.out.print("|\n");
		}
	}
}