package array;
import java.util.Scanner;
public class ArrayB06primeplus {
	public static void main(String[]args){
		int[] prime=new int [50];
		prime[0]=2;
		int count=3;
		for(int i=1;i<prime.length;i++){
			boolean m=false;
			while(!m){	
				m=true;
				for(int ii=0;ii<i;ii++){
					prime[i]=count;
				if(prime[i]%prime[ii]==0)
					m=false;
			}//for
				count++;
			}//while	
		}//for
		for (int i:prime){
			System.out.println(i);
		}
	}
}
