package multidarray;
import java.util.Scanner;
public class MultidarrayB10tec2 {
	public static void main(String[]args){
		int[][] a=new int[3][3];
		for(int i=0;i<a.length;i++){
			for(int ii=0;ii<a[i].length;ii++){
				a[i][ii]=(int)(2*Math.random());
				System.out.print(a[i][ii]);
			}
			System.out.println();
		}
		for(int i=0;i<a.length;i++){
			if(a[i][0]+a[i][1]+a[i][2]==0){
				System.out.println("row "+i+" is all 0");
			}
			if(a[0][i]+a[1][i]+a[2][i]==0){
				System.out.println("column "+i+" is all 0");
			}
			if(a[i][0]+a[i][1]+a[i][2]==3){
				System.out.println("row "+i+" is all 1");
			}
			if(a[0][i]+a[1][i]+a[2][i]==3){
				System.out.println("column "+i+" is all 1");
			}
		}
		if((a[0][0]+a[1][1]+a[2][2]==3)||(a[0][2]+a[1][1]+a[2][0]==3))
			System.out.println("the diagonal is all 1");
		if((a[0][0]+a[1][1]+a[2][2]==3)||(a[0][2]+a[1][1]+a[2][0]==0))
			System.out.println("the diagonal is all 0");
	}
}
