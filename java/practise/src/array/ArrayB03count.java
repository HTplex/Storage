package array;
import java.util.Scanner;
public class ArrayB03count {
	public static void main(String[]args){
		Scanner input=new Scanner(System.in);
		System.out.println("please input numbers(most 100)");
		int [] numbers=new int [100];
		int a=-1;
		int count=0;
		while(a!=0){
			a=input.nextInt();
			numbers[count]=a;
			count++;
		}
		int[] counta=new int [101];
		for(int i=0;i<count;i++){
			counta[numbers[i]]++;
		}
		java.util.Arrays.sort(numbers);
		for(int ii=1;ii<=100;ii++){
			if(counta[ii]!=0)
		System.out.println(ii+" occors "+counta[ii]+" times");
		}
	}
}
