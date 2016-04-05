package week6;
//import java.util.Scanner;
public class GBArrayBinHeaps<T extends Comparable<T>> {
	T[] a;
	int N;
	
	
	public void main(String[]args){
		/*GBArrayBinHeaps a=new GBArrayBinHeaps(10);
		T[] m={0,2,1,4,7,8,9,3,4,4,4};
		for(int i=0;i<11;i++){
			//a.push(m[i]);
			a.a[i]=m[i];
			a.N=i;
			//a.show();
			//System.out.println();
		}
		a.Sort();
		a.HeapSort();
		a.show();*/
		//System.out.println(a.N);
		//int w=a.N;
		//a.show();
		/*for(int i=0;i<w;i++){	
			System.out.print(a.popmax()+"\t");
			a.show();
			System.out.println();
		}*/
		
	}
	
	public void Sort(){
		for(int i=N/2; i>0; i--){
			down(i);
		}
	}
	
	public void show(){
		for(int i=1;i<=N;i++)
			System.out.print(a[i]);
	}
	public GBArrayBinHeaps(int n){
	//	this.a=(T[])new Comparable[n+1];this code is useful but i dont want to see the little warning thing
		N=0;
	}
	public void HeapSort(){
		int m=N;
		while(N>2){
			T k=popmax();
			this.a[N+1]=k;
			//this.show();
			
			//N--;
		}
		N=m;
	}
	
	public Boolean isEmpty(){
		return N==0;
	}
	
	public void down(int i){
		int k=i;
		while(2*k<N){
			int j=2*k;
			if(j<N&&less(a[j],a[j+1]))
					j++;
			
			if(less(a[k],a[j])){
				swap(a,k,j);
				k=j;
			}
			else break;
		}
	}
	
	public void up(int n){
		int k=n;
		while(k>0){
			int j=k/2;
			if(j>0&&less(a[j],a[k])){
				swap(a,k,j);
				k=j;
			}
			else break;
		}
	}
	public T popmax(){
		T m=a[1];
		swap(a,1,N);
		N--;
		down(1);
		
		return m;
	}
	
	public void push(T s){
		a[++N]=s;
		up(N);
	}
	
	public void swap(T[] a, int n, int m){
		T c=a[n];
		a[n]=a[m];
		a[m]=c;
	}
	
	public Boolean less(T a,T b){
		return a.compareTo(b)<0;
	}
	
}
