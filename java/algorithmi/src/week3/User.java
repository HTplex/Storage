package week3;

public class User {
	public static void main(String[]args){
		Integer[] a={6,5,4,3,2,1};
		//DASelectionSort<Integer> e=new DASelectionSort<Integer>();
		DBInsertionSort<Integer> k=new DBInsertionSort<Integer>();
		//DCShellSort<Integer> l=new DCShellSort<Integer>();
		DDShuffle<Integer> s=new DDShuffle<Integer>();
		long t=System.nanoTime();
		k.InsertionSort(a);
		System.out.println(System.nanoTime()-t);
		for(Integer i:a)
			System.out.println(i);
		for(int i=0;i<10;i++){
		s.shuffle(a);
		for(Integer i2:a)
			System.out.print(i2);
			System.out.println();
		}
	}
}
