package week2;

public class ZZDCResizeableArray {
	int CountFirst;
	int CountLast;
	int[] info;
	ZZDCResizeableArray(){
		info=new int[10];
		CountFirst=0;
		CountLast=0;
	}
	void enqueue(int a){
		this.info[this.CountLast++]=a;
		this.refresh();
	}
	int dequeue(){
		 return this.info[this.CountFirst++];
	}
	void resize(int i){
		int[] a=new int[i];
		for(int m=0;m<i;m++){
			a[m]=info[m];
			info=a;
		}
	}
	void refresh(){
		if(this.CountLast==this.info.length){
		for(int i=this.CountFirst;i<=this.CountLast;i++){
			this.info[i-this.CountFirst]=this.info[i];
		}
		for(int i=this.CountLast+1;i<this.info.length;i++)
			this.info[i]=0;
		this.CountFirst-=this.CountFirst;
		this.CountLast-=this.CountFirst;
		if(this.CountLast<=(this.info.length)/4)
			resize(this.info.length/2);
		}
	}
	
	
	
	
	
	
	
	public static void main(String[]args){
		
	}
	
}
