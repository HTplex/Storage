package multidarray;
import java.util.Scanner;
public class MultidArrayA01creat {
	public static void main(String[]args){
		int[][] matrix=new int[5][5];
		int[][] trimatrix=new int[5][];
		trimatrix[4]=new int[5];
		trimatrix[3]=new int[4];
		trimatrix[2]=new int[3];
		trimatrix[1]=new int[2];
		trimatrix[0]=new int[1];
		System.out.printf("%3d%3d%3d%3d%3d%3d",matrix.length,matrix[0].length,matrix[1].length,matrix[2].length,matrix[3].length,matrix[4].length);
		System.out.println();
		System.out.printf("%3d%3d%3d%3d%3d%3d",trimatrix.length,trimatrix[0].length,trimatrix[1].length,trimatrix[2].length,trimatrix[3].length,trimatrix[4].length);
		
	}
}
