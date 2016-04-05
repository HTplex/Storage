package week2;

public class DBResizeableArray {
	String[] a;
	int count;
	DBResizeableArray(){
		this.a=new String[10];
		this.count=0;
	}
	void expand(){
		String[] b=new String[2*a.length];
		for (int i=0;i<a.length;i++){
			b[i]=a[i];
		}
		a=b;
	}
	
	void push(String s){
		a[count]=s;
		count++;
		if(count==a.length)
			this.expand();
	}
	
	void pop(){
		count--;
		System.out.println(a[count]);
		a[count]=null;
		//if(count<a.length/2)					//doubling halfing doubling halfing...
		if(count<a.length/4)
			this.shrink();
	}
	void shrink(){
		String[] b=new String[a.length/2];
		for(int i=0;i<a.length/2;i++)
			b[i]=a[i];
		a=b;
	}
	
	void resize(int n){
		String[] b=new String[n];
		for(int i=0;i<n;i++)
			b[i]=a[i];
		a=b;
	}
	
	public static void main(String[]args){
		
	}
}
