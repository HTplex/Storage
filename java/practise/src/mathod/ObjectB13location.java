package mathod;
import java.util.Scanner;
public class ObjectB13location {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("input colume");
		int c=input.nextInt();
		System.out.println("input line");
		int l=input.nextInt();
		int[][] a=new int[c][l];
		for(int i=0;i<a.length;i++){
			for(int ii=0;ii<a[i].length;ii++){
				System.out.print("input a["+i+"]["+ii+"]");
				a[i][ii]=input.nextInt();
			}
		}
		System.out.println("the location is "+location(a)[0]+" "+location(a)[1]);
	}

public static int[] location(int[][] a){
	int[] m=new int [2];
	int max=a[0][0];
	for(int i=0;i<a.length;i++){
		for(int ii=0;ii<a[i].length;ii++){
			if(max<a[i][ii]){
				max=a[i][ii];
				m[0]=i;
				m[1]=ii;
			}
		}
	}
	return m;
}
}
