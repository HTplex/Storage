package lab;
import java.util.Scanner;
public class Mod {
	public static void main(String[]args){
		Scanner in=new Scanner(System.in);
		

		final int stu=1000000007;
		while(in.hasNext()){
			long temp=1;
			long n=in.nextInt();
			for(long i=2;i<=n;i++){
				long down=i;
				long nn=n-i;
				int step=(int)(Math.log(stu)/Math.log(i));
				long up=(long)(Math.pow(down,step));
				while(step<nn){
					down*=up;
					nn-=step;
					if(down>=stu){
						down%=stu;
					}
				}
				down*=Math.pow(i, nn);
				if(down>stu) down%=stu;
				temp*=down;
				if(temp>stu) temp%=stu;
			}
			
			System.out.println(temp);
			
		}
		in.close();
	}
}
