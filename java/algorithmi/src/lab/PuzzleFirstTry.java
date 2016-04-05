package lab;
import java.util.Scanner;
public class PuzzleFirstTry {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int N=input.nextInt();
		int [][] puz=new int[N][N];
		for(int i=0;i<N;i++){
			for(int i1=0;i1<N;i1++){
				puz[i][i1]=input.nextInt();
			}
		}
		
		input.close();
	}
	public static void solve(int[][] puz,int posx, int posy,int dir){
		puz[posx][posy]=1;
		
	}
}
