package collection;
import java.util.Scanner;
public class LZcompression {
	public static void main(String[]args)throws Exception{
		
		Scanner input=new Scanner(System.in);
		String in=input.nextLine();
		String[] index=new String[10000];
		String[] indexex=new String[10000];
		StringBuilder cache=new StringBuilder();
		String caches =new String();
		int c=0;
		int d=0;
		for(int iq=0;iq<index.length;iq++){
			index[iq]="*";
			indexex[iq]="*";
		}
		
		for(int i=0;i<in.length();i++){
			cache.append(in.charAt(i));
			caches=cache.toString();
			
			//	System.out.println(caches);
			
		if(!hasone(caches,indexex)){
			if(caches.length()==1){
				index[c]=caches;
				indexex[c]=caches;
			c++;
			}
			else{
				String s=new String();
				s=s.valueOf(d);
				index[c]=s+in.charAt(i);
				indexex[c]=caches;
				c++;
			}
			cache.delete(0, caches.length());
		}
		else {
			d=whereis(caches,indexex);
			}
		}
		if(cache.length()!=0)
			index[c]=index[whereis(caches,indexex)];
		
		for(int w=0;w<index.length;w++){
			if(!index[w].equals("*"))
			System.out.print(index[w]);
		}  
		input.close();
	}
	public static boolean hasone(String a,String[] b){
		boolean bo=false;
		for(int i=0;i<b.length;i++){
			if(a.equals(b[i]))
				bo=true;
		}
		return bo;
	}
	public static int whereis(String a,String[] b){
		for(int i=0;i<b.length;i++){
			if(a.equals(b[i]))
				return i;
		}
		return -1;
	}
}
