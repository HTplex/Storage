package week3;
public class DBInsertionSort<T extends Comparable<T>> {
	public static void main(String[]args){
		//nothing here
	}
	void InsertionSort(T[] a){
		DASelectionSort<T> k=new DASelectionSort<T>();
		for(int i=0;i<a.length-1;i++){
			if(i%(a.length/100)==0)
				System.out.println("progress: "+i/(a.length/100)+"%");
			for(int i2=i;i2>=0&&a[i2+1].compareTo(a[i2])<0;i2--)
			k.swap(a, i2+1, i2);
		}
		System.out.println("progress: 100%");
	}
}
