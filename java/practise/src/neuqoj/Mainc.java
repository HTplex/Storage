package neuqoj;
import java.util.Scanner;
public class Mainc {
	public static void main(String[]args){
		int [] a={2,3,5,7};
		String s=new String();
		String ss=new String("2357");
		int q,w,e,r,t,m,n;
		for(int i=0;i<4;i++){
			q=a[i]; 
			for(int i1=0;i1<4;i1++){
				w=a[i1]; 
				for(int i2=0;i2<4;i2++){
					e=a[i2]; 
					for(int i3=0;i3<4;i3++){
						r=a[i3]; 
						for(int i4=0;i4<4;i4++){
							t=a[i4];
							m=100*q+10*w+e;
							if(m*r>=1000&&m*r<10000&&m*t>=1000&&m*t<10000&&m*(10*r+t)>=10000&&m*(10*r+t)<100000){
								int u=m*(10*r+t);
								s=s.valueOf(u);
								char[] cc=s.toCharArray();
									java.util.Arrays.sort(cc);
									for(char g:cc)
										System.out.print(g);
									System.out.println(q+" "+w+" "+e+" "+r+" "+t);
								
					
							}
						}
					}
				}
			}
		}
		
	}
}
 