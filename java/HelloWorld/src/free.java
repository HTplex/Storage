import java.util.Scanner; 
public class free{
	public static void main(String[]args){
		int [][][] b=new int[2][2][3];
		int [][]a={{1,2,3},{4,5,6},{7,8,9}};
		b[0]=java.util.Arrays.copyOfRange(a, a[1][1],a[2][2]);
		//b[1]=java.util.Arrays.copyOfRange(a, a[1][2],a[2][1]);
		b[2]=java.util.Arrays.copyOfRange(a, a[1][0],a[2][1]);
		
	}
}
