package week1;

public class BCQuickUnionWithBug {
	private int[] id;
	public BCQuickUnionWithBug(int N){
		id=new int[N];
		for(int i=0;i<N;i++){
			this.id[i]=i;
		}
	}
	
	void union(int a,int b){
		if(connected(a,b))
			System.out.println(a+" & "+b+" is already connected");
		else{
			id[a]=b;
		}
	}
	Boolean connected(int a,int b){
		while(id[a]!=a){
			a=id[a];
		}
		while(id[b]!=b){
			b=id[b];
		}
		return a==b;
	}
	public static void main(String[]args){
		
	}
}
