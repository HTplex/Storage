package neuqoj;
import java.util.Scanner;
public class A1069 {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int i,j,k,l,m=0;
		i=input.nextInt();
		for(j=1;j<=i;j++){
			k=input.nextInt();
			if(k%2!=0)
				System.out.println("Oops!");
			else if(k==2){
				
				m=121;
			System.out.println(m);
			}
			else {
			l=get(5,k-1)-get(1,(int)(k/2))+1;
			m=(l*l)%2009;
			System.out.println(m);
			}
			
		}
		
	}
	public static int get(int a, int b){
		int s=a;
		for(int t=1;t<=b;t++){
			s*=10;
			s%=2009;
		}
		return s;
	}
}
