package multidarray;
import java.util.Scanner;
public class MultidarrayB14tecplus {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("input the a of the matrix");
		int z=input.nextInt();
		int[][] a=new int[z][z];
		for(int i=0;i<a.length;i++){
			for(int ii=0;ii<a[i].length;ii++){
				a[i][ii]=(int)(2*Math.random());
				System.out.print(a[i][ii]);
			}
			System.out.println();
		}
		int []maxc=new int [a.length];
		int []maxf=new int [a.length];
		int maxcc=0;
		int maxcd=0;
		for(int i=0;i<a.length;i++){
			for(int ii=0;ii<a[i].length;ii++){
				maxc[i]+=a[i][ii];
				maxf[i]+=a[ii][i];
			}
			maxcc+=a[i][i];
			maxcd+=a[i][a.length-1-i];
		}
		for(int i=0;i<a.length;i++){
			if(maxc[i]==0){
				System.out.println("row "+i+" is all 0");
			}
			if(maxf[i]==0){
				System.out.println("column "+i+" is all 0");
			}
			if(maxc[i]==a.length){
				System.out.println("row "+i+" is all 1");
			}
			if(maxf[i]==a.length){
				System.out.println("column "+i+" is all 1");
			}
		}
		if((maxcc==a.length)||(maxcd==a.length))
			System.out.println("the diagonal is all 1");
		if((maxcc==0)||(maxcd==0))
			System.out.println("the diagonal is all 0");
	}
}
