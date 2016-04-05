package multidarray;
import java.util.Scanner;
public class MultidarrayB16doublesort {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.print("please input the number of doubles you want");
		int a=input.nextInt();
		int[][] ha=new int[a][2];
		for(int i=0;i<ha.length;i++){
			
			ha[i][0]=input.nextInt();
			ha[i][1]=input.nextInt();
		}
		sort (ha);
				for(int i=0;i<ha.length;i++){
					System.out.println(ha[i][0]+" "+ha[i][1]);
					
				}
	}
	public static int[][] sort(int[][] a){
		int m=a.length;
		for(m=2;m<a.length;m++){
		for(int i=0;i<a.length;i++){
			int []temp=new int[a[i].length];
			temp=a[m-1];
			int ii=0;
				while(temp[0]>a[ii][0]||(temp[0]==a[ii][0]&&temp[1]>a[ii][1])){
					ii++;
				}
				for(int c=m-2;c>=ii;c--){
					a[c+1]=a[c];
			}
				a[ii]=temp;
		}
		}
		return a;
	}
}
