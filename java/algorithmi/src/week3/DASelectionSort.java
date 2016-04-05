package week3;
public class DASelectionSort<T extends Comparable<T>> {
	public static void main(String[]args){
		/* 
		Integer [] a={1,3,4,2,3,4,3,2,32,4,5,3,2,3,3,4,34,2};
		SelectionSort(a);
		for(int i:a)
			System.out.println(i);*/
	}
	public void SelectionSort(T [] a){
		for(int i=0;i<a.length-1;i++) 
			swap(a,minFT(a,i,a.length-1),i);
	}
	public int minFT(T[] a, int from, int to){
		int m=from;
		for(int i=from+1;i<=to;i++)
			if(a[m].compareTo(a[i])>0) m=i;
		return m;
	}
	public void swap(T a, T b){      //this is not working, not the same var
		T temp=a;
		a=b;
		b=temp;
	}
	public void swap(T[] a, int n, int m){
		T temp=a[n];
		a[n]=a[m];
		a[m]=temp;
	}

}
