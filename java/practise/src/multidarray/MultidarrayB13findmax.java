package multidarray;
import java.util.Scanner;
public class MultidarrayB13findmax {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int max=0;
		int x=0;
		int y=0;
		System.out.print("input column");
		int a=input.nextInt();
		System.out.println("input row");
		int b=input.nextInt();
		int[][] c=new int [a][b];
		for(int i=0;i<c.length;i++){
				System.out.print("input a["+i+"](array)");
				for(int ii=0;ii<c.length;ii++){
					c[i][ii]=input.nextInt();
			}
		}
		for(int i=0;i<c.length;i++){
			for(int ii=0;ii<c.length;ii++){
			if(max<c[i][ii]){
				max=c[i][ii];
				x=i;
				y=ii;
			}
			}
		}
		System.out.println("the biggest number is c["+x+"]["+y+"]");
	}
}
