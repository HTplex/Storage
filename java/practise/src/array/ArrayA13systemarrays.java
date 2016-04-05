package array;
import java.util.Scanner;
public class ArrayA13systemarrays {
	public static void main(String[]args){
	Scanner input=new Scanner(System.in);
	System.out.println("please input what you want in the list");
	System.out.println("1:java.util.Arrays.sort(a);\n"
			+ "2:java.util.Arrays.binarySearch(b, x)\n"
			+ "3:java.util.Arrays.equals(c, d)\n"
			+ "4:java.util.Arrays.fill(e, q[0],q[1],z)\n"
			+ "5:java.util.Arrays.fill(e1,z1)");
	int i=input.nextInt();
	switch (i){
	case 1:
		System.out.println("please input 5 numbers");
		int[] a=new int[5];
		for (int ii=0;ii<5;ii++)
			a[ii]=input.nextInt();
		java.util.Arrays.sort(a);
		for (int ii=0;ii<5;ii++)
			System.out.println(a[ii]);
	break;
	case 2:
		System.out.println("please input 5 numbers");
		int[] b=new int[5];
		for (int ii=0;ii<5;ii++)
			b[ii]=input.nextInt();
		System.out.println("please input the number you want to find(you can be bad)");
		int x=input.nextInt();
			System.out.println(java.util.Arrays.binarySearch(b, x));
	break;
	case 3:System.out.println("please input 5 numbers");
		int[] c=new int[5];
		for (int ii=0;ii<5;ii++)
		c[ii]=input.nextInt();
		System.out.println("please input the numbers again");
		int[] d=new int[5];
		for (int ii=0;ii<5;ii++)
		d[ii]=input.nextInt();
		System.out.println("is the number the same?"+java.util.Arrays.equals(c, d));
		break;
	case 4:int[]e={1,2,3,4,5,6,7,8,9,0};
		for(int u:e)
		System.out.print(u+" ");
		System.out.println("input the number you want to input");
		int z=input.nextInt();
		System.out.println("input the 2 place you want to replace");
		int[] q=new int [2];
		int k=input.nextInt();
		int l=input.nextInt();
		java.util.Arrays.fill(e,k-1,l,z);
		for(int u:e)
			System.out.print(u+" ");
			break;
	case 5:int[]e1={1,2,3,4,5,6,7,8,9,0};
		for(int u1:e1)
		System.out.print(u1+" ");
		System.out.println("input the number you want to input");
		int z1=input.nextInt();
		java.util.Arrays.fill(e1,z1);
		for(int u1:e1)
		System.out.print(u1+" ");
		break;
		}
	}
}
