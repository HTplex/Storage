package week3;

public class DCShellSort<T extends Comparable<T>> {
	public void ShellSort(T[] a){
		int n=1;
		while(n<a.length/3) n*=3;n++;
		while(n>0){
		AdvInsertionSort(a,n);
		System.out.println("n = "+n);
		n/=3;
		}
	}

	public void AdvInsertionSort(T[] a, int n){
		DASelectionSort<T> k=new DASelectionSort<T>();
		for(int i=0;i<a.length-n;i++)
			for(int it=i;it>=0&&a[it].compareTo(a[it+n])>0;it-=n)
				k.swap(a, it, it+n);
	}
	
	
	
	
	public static void main(String[]args){
		//nothing here
	}
}
