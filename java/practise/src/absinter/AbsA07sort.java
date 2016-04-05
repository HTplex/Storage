package absinter;
import java.util.*;
public class AbsA07sort {
	public static void main(String[]args){
		Integer s;
		Integer[] a={new Integer(1),new Integer(8),new Integer(9),new Integer(6)};
		sort(a);
		for(int i:a)
		System.out.println(i);
	}
	public static void sort(Comparable[] a){
		for(int n=1;n<a.length;n++){
		Comparable cache=0;
		for(int i=n;i>0;i--){
			int nn=n;
			if(a[i].compareTo(a[i-1])>0){
				cache=a[i];
				a[i]=a[i-1];
				a[i-1]=cache;
			}
			}
		}
		
	}
}
