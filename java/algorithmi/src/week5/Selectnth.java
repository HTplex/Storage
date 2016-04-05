package week5;

public class Selectnth<T extends Comparable<T>> {
	public static void main(String[]args){
		//i am going to start input test here
		//Integer[] a={3,2,1,6,5,4,9,8,7,0};
		//System.out.println(Select(a,0,a.length-1,3));
	}
	public T Select(T[] a,int from, int to, int n){
		FAQuickSort<T> fq=new FAQuickSort<>();
		if(n==fq.paratition(a, from, to))
			return a[n];
		else if(n>fq.paratition(a, from, to)){
			return Select(a,fq.paratition(a, from, to)+1,to,n);
		}
		else return Select(a,from,fq.paratition(a, from, to)+1,n);
	}
}
