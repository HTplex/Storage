package week3;

public class DDShuffle<T extends Comparable<T>> {
	public void shuffle(T[] a){
		DASelectionSort<T> d=new DASelectionSort<T>();
		for(int i=0;i<a.length;i++){
			int r=i+(int)(Math.random()*(a.length-i));
			d.swap(a, i, r);
		}
	}
	public static void main(String[]args){
		//still nothing here
	}
}
