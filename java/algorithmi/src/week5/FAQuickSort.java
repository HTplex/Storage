package week5;

public class FAQuickSort<T extends Comparable<T>> {
	
	
	public int paratition(T[] a, int from, int to){
		int i=from;
		int j=to+1;
		week3.DASelectionSort<T> sw=new week3.DASelectionSort<>();
		while(true){
			while(i<to&&a[from].compareTo(a[++i])>0){}
				//if(i>=to) break;
			while(j>from&&a[from].compareTo(a[--j])<0){}
				//if(j<=from) break;
			
			if(j<=i){
				sw.swap(a, from, j);
				break;
			}
			else sw.swap(a, i, j);
		}
		return j;
	}
	public void QuickSort(T[] a,int from, int to){
		if(from>=to) return;
		int mid=paratition(a,from,to);
		QuickSort(a,from,mid-1);
		QuickSort(a,mid+1,to);
	}
	public void QuickSort(T[] a){
		week3.DDShuffle<T> ds=new week3.DDShuffle<>();
		ds.shuffle(a);
		QuickSort(a,0,a.length-1);
	}
	
	

	
	
	
	public void AdvQuickSort(T[]a, int from, int to){
		int f=from;
		int m=from;
		int s=to;
		week3.DASelectionSort<T> d=new week3.DASelectionSort<>();
		if(from>=to) return;
		T key=a[from];
		while(m<=s){
			int Comp=a[m].compareTo(key);
			if(Comp==0) m++;
			else if(Comp<0) d.swap(a, m++, f++);
			else d.swap(a,m,s--);
		}
		AdvQuickSort(a,from,f);
		AdvQuickSort(a,s+1,to);
	}
	
	public static void main(String[]args){
		//Integer[] a={4,9,8,7,6,5,4,4,3,3,4,4,5,3,3,4,3,3,3,4,3,4,3,3,2,1};
		//AdvQuickSort(a,0,a.length-1);
		//paratition(a,0,0);
		//for(int i:a){
		//	System.out.println(i);
		//}
	}
}
