package multidarray;
import java.util.Scanner;
public class free {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int [][] chess=new int [30][20];
		int count=0;
		int a=0;
		boolean b=true;
		while(b){
			count++;
			a=0;
			int i=0;
			printchess(chess);
			if(count%2==0){
				System.out.println("its R's turn");
				a=input.nextInt();
				while(chess[a][i]!=0)
				i++;
				chess[a][i]+=1;
			}//if
			if(count%2!=0){
			System.out.println("its Y's turn");
			a=input.nextInt();
			while(chess[a][i]!=0)
			i++;
			chess[a][i]-=1;
		}//if
			b=!MultidarrayB19chesscheck.check4(chess);
		}//while
		printchess(chess);
		if(count%2==0)System.out.println("R win!");
		if(count%2!=0)System.out.println("Y win!");
		if(count==600)System.out.println("TIE");
	}
	
	public static void printchess(int[][] a){
		char[][] pr=new char [a.length][a[0].length];
		for(int e=0;e<a.length;e++)
		java.util.Arrays.fill(pr[e],' ');
		for(int i=0;i<a.length;i++){
			for(int ii=0;ii<a[i].length;ii++){
				if (a[i][ii]==1)
					pr[i][ii]='R';
				if (a[i][ii]==-1)
						pr[i][ii]='Y';
			}
		}
		for(int i3=0;i3<a[1].length;i3++){
			for(int i4=0;i4<a.length;i4++){
				System.out.print("|"+pr[i4][a[1].length-1-i3]);
			}
			System.out.print("|\n");
		}
		
		for(int i=0;i<30;i++) System.out.print(" "+i%10);
		System.out.println();
	}
}
