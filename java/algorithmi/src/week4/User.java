package week4;

public class User {
	public static void main(String[]args){
		Integer[] a={5,1,2,3,1,3,3,4,5,3,5,6};
		Integer[] b=new Integer[a.length];
		EAMergeSort<Integer> k=new EAMergeSort<Integer>();
		k.MergeSort(a, b, 0, a.length-1);
		for(int i:a)
			System.out.println(i);
	}
}
