package week4;

public class EAMergeSort<T extends Comparable<T>> {
	
	public static void main(String[]args){
		//why always nothing here
	}
	void merge(T[] a,T[] temp, int from, int mid, int to){
		//T[] temp=new T[a.length]; cannot do this
			for(int i=from;i<=to;i++)
				temp[i]=a[i];
			int f=from;
			int s=mid+1;
			for(int i=from;i<=to;i++){
				if(f>mid) a[i]=temp[s++];
				else if(s>to) a[i]=temp[f++];
				else if(temp[f].compareTo(temp[s])<0)
					a[i]=temp[f++];
				else a[i]=temp[s++];
			}
	}
	public void MergeSort(T[] a,T[] temp, int from, int to){
		
		
		if(from==to) return;
		int mid=(from+to)/2;
			MergeSort(a,temp,from,mid);
			MergeSort(a,temp,mid+1,to);
			merge(a,temp,from,mid,to);
	}
}
