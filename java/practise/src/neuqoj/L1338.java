package neuqoj;
import java.util.Scanner;
public class L1338 {
	public static void main(String[]args){
		String a=new String();
		String bb=new String();
		for (int i=203880;i<=987654;i++){
			boolean b=true;
			boolean b2=true;
			a=a.valueOf(i);
			char[] c=a.toCharArray();
			java.util.Arrays.sort(c);
			for(int ii=0;ii<c.length-1;ii++){
				if(c[ii]==c[ii+1])
					b=false;
			}//for
			if(b){
				long il=(long)i*i;
				bb=bb.valueOf(il);
				char[] cc=bb.toCharArray();
				for(int i2=0;i2<cc.length;i2++){
					for(int i3=0;i3<c.length;i3++){
						if(cc[i2]==c[i3])
							b2=false;
					}
				}

				if(b2){
					System.out.println(i);
				b2=true;
				}
			}
		}
	}
}
