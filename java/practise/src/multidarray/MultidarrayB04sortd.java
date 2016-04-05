package multidarray;
import java.util.Scanner;
public class MultidarrayB04sortd {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
			System.out.println("please input the number of employs");
			int s=input.nextInt();
			int[][] m=new int[s][7];
			String [] w=new String[s];
			for(int i=0;i<w.length;i++){
				System.out.println("name ["+"]");
				w[i]=input.next();
			}
			for(int i=0;i<m.length;i++){
				for(int i2=0;i2<m[i].length;i2++){
					System.out.println(w[i]+" "+i2);
					m[i][i2]=input.nextInt();
				}
			}
		int [] sum=new int [s];
		for(int i=0;i<m.length;i++){
			for(int i2=0;i2<m[i].length;i2++){
				sum[i]+=m[i][i2];
			}
		}
		
		
		String [] name=new String[70*s];
		for(int i=0;i<s;i++){
			name [sum[i]]=w[i];
		}
		java.util.Arrays.sort(sum);
		for(int i=0;i<s;i++){
			System.out.println(name[sum[i]]);
		}
	}
}
