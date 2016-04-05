package multidarray;
import java.util.Scanner;
public class MultidarrayB17storm {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int numb=input.nextInt();
		double limit=input.nextDouble();
		double [] money=new double[numb];
		double [][] current=new double[numb*numb][3];
		int c=0;
		int[] list=new int[numb];
		int t=0;
		for(int i=0;i<money.length;i++){
			money [i]=input.nextDouble();
			int d=input.nextInt();
			for(int i1=0;i1<d;i1++){
				current[c][0]=input.nextDouble();
				current[c][1]=input.nextDouble();
				current[c][2]=i1;
				money [i]+=current[c][1];
				c++;
			}
		}//input
		for(int i=0;i<money.length;i++){
		if(money[i]<limit){
			for(int i3=0;i3<current.length;i3++){
				if (current[i3][2]==i)
					money[(int)current[i3][2]]-=current[i3][1];
			}//for
		}
		}
			for(int i=0;i<money.length;i++){
				if(money[i]<limit){
					list[t]+=(i+1);
					}
			t++;
		}
		for(double w:money){
			System.out.println(w);
		}
		for(int g:list)
		System.out.println(g);	
	}
}
