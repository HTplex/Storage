package collection;
import  java.util.Scanner;
public class LZdiscompression {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		String s=input.nextLine();
		String[] index=new String[s.length()];
		String[] indexex=new String[s.length()];
		StringBuilder sb=new StringBuilder();
		String sbs=new String();
		int p=0;
		for(int i=0;i<index.length;i++){
			index[i]="*";
		}
		//Character c=new Character('s');
		int count=0;
		boolean bo=true;
		Character[] c=new Character[s.length()];
		for(int iq=0;iq<s.length();iq++){
			c[iq]=s.charAt(iq);
		}
		
			for(int i=0;i<s.length();i++){
				
				
				if((!c[i].isDigit(c[i]))&&bo){
					index[count]=c[i].toString();
					bo=false;
					count++;
				}
				else{
				if(c[i].isDigit(c[i])){
					Integer r=0;
					//if(!c[i+1].isDigit(c[i+1])){
						String ss=c[i].toString();
						r=Integer.parseInt(ss);
					//}
					while(c[i+1].isDigit(c[i+1])){
						//String ss=c[i].toString();
						//r=Integer.parseInt(ss);
						
						ss=c[i+1].toString();
						Integer r1=Integer.parseInt(ss);
						r=r*10+r1;
						i++;
					}

					index[count]=index[r]+c[i+1].toString();
					count++;
					i++;
				}
				else{
					index[count]=c[i].toString();
					count++;
				}
				}
			}
			for(int yi=0;yi<index.length;yi++){
				if(index[yi]!="*")
			System.out.print(index[yi]);
			}
	}
}
