package multidarray;
import java.util.Scanner;
public class MultidarrayB19chesscheck {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input column");
		int a=input.nextInt();
		System.out.println("please input line");
		int b=input.nextInt();
		int [][] tec=new int[a][b];
		for(int i=0;i<tec.length;i++){
			for(int i1=0;i1<tec[i].length;i1++){
				tec[i][i1]=(int)(0*Math.random());
				tec[5][2]=1;
				System.out.print(tec[i][i1]);
			}
			System.out.println();
		}
		if(check4(tec))
			System.out.print("there do has a line");
	}
	public static boolean check4(int[][] a){
		boolean b=false;
		for(int i=0;i<a.length;i++){
			for(int i1=0;i1<a[0].length-4;i1++){
				if(a[i][i1]!=0){
				if(a[i][i1]==a[i][i1+1]&&a[i][i1+1]==a[i][i1+2]&&a[i][i1+2]==a[i][i1+3])
					b=true;
				}
			}
			}
			for(int i=0;i<a[0].length;i++){
				for(int i1=0;i1<a.length-4;i1++){
					if(a[i1][i]!=0){
				if(a[i1][i]==a[i1+1][i]&&a[i1+1][i]==a[i1+2][i]&&a[i1+2][i]==a[i1+3][i])
					b=true;	
					}
				}
		}
					
		for(int i=0;i<a.length-4;i++){
			for(int i1=0;i1<a[0].length-4;i1++){
				if(a[i][i1]!=0){
				if(a[i][i1]==a[i+1][i1+1]&&a[i+1][i1+1]==a[i+2][i1+2]&&a[i+2][i1+2]==a[i+3][i1+3])
					b=true;
				}
			}
		}
		for(int i=0;i<a[0].length-4;i++){
			for(int i1=0;i1<a.length-4;i1++){
				if(a[a.length-1-i][i1]!=0){
				if(a[a.length-1-i][i1]==a[a.length-2-i][i1+1]&&a[a.length-2-i][i1+1]==a[a.length-3-i][i1+2]&&a[a.length-3-i][i1+2]==a[a.length-4-i][i1+3])
				b=true;
				}
			}
			}
		return b;
	}
}
