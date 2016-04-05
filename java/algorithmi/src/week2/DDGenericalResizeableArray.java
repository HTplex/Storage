package week2;

public class DDGenericalResizeableArray<E> {
	E[] a;
	int count;
	DDGenericalResizeableArray (){
		//this.a=(E[])(new Object[10]);
		this.count=0;
	}
	void expand(){
		resize(2*this.a.length);
	}
	
	void push(E s){
		a[count]=s;
		count++;
		if(count==a.length)
			this.expand();
	}
	
	void pop(){
		count--;
		System.out.println(a[count]);
		a[count]=null;
		////////////if(count<a.length/2			//doubling halfing doubling halfing...
		if(count<a.length/4)
			this.shrink();
	}
	void shrink(){
		resize(this.a.length/2);
	}
	
	void resize(int n){
	/*	E[] b=(E[])(new Object[n]);				//ATTENTION: CAST IS DANGEROUS,AVOID!
		for(int i=0;i<n;i++)
			b[i]=a[i];
		a=b;*/
	}
	
	public static void main(String[]args){
		
	}
}
