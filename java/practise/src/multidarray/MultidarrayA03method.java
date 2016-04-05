package multidarray;
import java.util.Scanner;
public class MultidarrayA03method {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		int[][] hei=new int [5][5];
		for(int i=0;i<hei.length;i++){
			for(int i2=0;i2<hei[i].length;i2++){
				System.out.println("["+i+"]"+"["+i2+"]");
				hei[i][i2]=input.nextInt();
			}
		}
		System.out.println(sum(hei));
	}
	public static int sum(int[][] a){
		int sum=0;
		for(int i=0;i<a.length;i++){
			for(int i2=0;i2<a[i].length;i2++){
				sum+=a[i][i2];
			}
		}
		return sum;
	}
}
