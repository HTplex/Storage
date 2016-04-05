package lab;
public class Swap {
	public static void main(String[]args){

		int[] c={1,2,3,4,5};
		swap(c,0,2);
		for(int i:c)
			System.out.println(i);
	
	}
	static void swap(int a, int b){
		int c=a;
		a=b;
		b=c;
	}
	static void swap(int [] a, int n, int m){
		int temp=a[n];
		a[n]=a[m];
		a[m]=temp;
	}
}
