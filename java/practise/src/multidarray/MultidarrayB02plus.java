package multidarray;
import java.util.Scanner;
public class MultidarrayB02plus {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("input the a of the matrix");
		int a=input.nextInt();
		int [][] s=new int [a][a];
		for(int i=0;i<s.length;i++){
			for(int i1=0;i1<s[i].length;i1++){
				System.out.print("input["+i+"]["+i1+"]");
				s[i][i1]=input.nextInt();
			}
		}
		int sum=0;
		for(int i=0;i<s.length;i++){
			sum+=s[i][i];
			sum+=s[i][s.length-i-1];
		}
		for(int i=0;i<s.length;i++){
			for(int i1=0;i1<s[i].length;i1++){
				System.out.print(s[i][i1]);
			}
			System.out.println();
		}
		if(a%2==0)
		System.out.println(sum);
		else System.out.println(sum-s[(a-1)/2][(a-1)/2]);
	}
}
