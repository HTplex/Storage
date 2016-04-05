package neuqoj;
import java.util.Scanner;
public class Oj1309123456789 {
	public static void main(String[]args){
		int m=0;
		String [] s=new String[3];
		char[][] c=new char[3][3];
		char[] cc=new char[9];
		for(int i=1;i<=3;i++){
			for(int i1=1;i1<10;i1++){
				for(int i2=1;i2<10;i2++){
					if(i!=i1&&i1!=i2){
						m=100*i+10*i1+i2;
						if(3*m<1000){
						s[0]=s[0].valueOf(m);
						s[1]=s[1].valueOf(2*m);
						s[2]=s[2].valueOf(3*m);
						c[0]=s[0].toCharArray();
						c[1]=s[1].toCharArray();
						c[2]=s[2].toCharArray();
						int u=0;
						for(int i3=0;i3<3;i3++){
							for(int i4=0;i4<3;i4++){
								cc[u]=c[i3][i4];
								u++;
							}
						}
						java.util.Arrays.sort(cc);
						String sss=new String();
							sss=sss.valueOf(cc);
							if(sss.equals("123456789"))
							System.out.println(s[0]+","+s[1]+","+s[2]);	
						//if(sss.equals("123456789"))
						
						//System.out.println(s[0]+","+s[1]+","+s[2]);	
						/*java.util.Arrays.sort(cc);
						for(int is=0;is<9;is++)
						System.out.print(cc[is]);
						System.out.println();*/
						}
					}
				}
			}
		}
	}
}
