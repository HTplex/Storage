package array;
import java.util.Scanner;
public class ArrayB21bean{
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input the number of the slots");
		int s=input.nextInt();
		System.out.println("please input the number of the beans");
		int b=input.nextInt();
		int [] slot=new int [s];

		for(int i=0;i<b;i++){
			int sum=0;
			for(int i2=0;i2<s-1;i2++){
				int ss=(int)(2*Math.random());
				sum+=ss;
				if(ss==1)System.out.print('R');
				if(ss==0)System.out.print('L');
			}
			slot[sum]++;
			System.out.println();
		}
			for(int w:slot)
				System.out.print(w+" ");
			System.out.println();
			printbean(slot);
	}
	public static void printbean(int [] a){
		int max=max(a);
		for(int i=0;i<max;i++){
			for(int k=0;k<a.length;k++){
				if(max-a[k]<=0){
					System.out.print("0");
					
				}
					else {
						System.out.print(" ");
						a[k]++;
					}
			}
			System.out.println();
		}
	}
	public static int max(int [] a){
		int max=0;
		for(int i=0;i<a.length;i++){
			if (max<a[i])
			max=a[i];
		}
		return max;
	}
}
