package week1;

public class BCQuickUnionFixing {
	private int[] id;
	BCQuickUnionFixing(int N){
		id=new int[N];
		for(int i=0;i<N;i++){
			id[i]=i;
		}
	}
	public void union(int a, int b){
		if (connected(a,b)){
			System.out.println("herb is smoking again!");
		}
			else
				id[root(b)]=root(a);
	}
	
	public boolean connected(int a, int b){
		return root(a)==root(b);
	}
	
	private int root(int a){
		while(id[a]!=a)
			a=id[a];
		return a;
	}
	
	public static void main(String[]args){	
	}
}
