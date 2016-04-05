package loop;
import java.util.Scanner;
public class LoopB38hex {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input the number you want to change to hex");
		long put=input.nextLong();
		long H=(long)(Math.pow(2, 60));
		while(H>put){
			H/=16;
		}
		for(long i=H;i>0;i/=16){
			if (put>=i){
				int k=(int)(put/i);
				if (k<10)
				System.out.print(k);
				else {
					switch(k){
					case 10:System.out.print("A");break;
					case 11:System.out.print("B");break;
					case 12:System.out.print("C");break;
					case 13:System.out.print("D");break;
					case 14:System.out.print("E");break;
					case 15:System.out.print("F");break;
					}
				}
				put-=i*k;
			}
			else { 
				System.out.print("0");
			}
		}
	}
}
