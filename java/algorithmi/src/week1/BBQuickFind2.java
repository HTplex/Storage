package week1;
public class BBQuickFind2 {
	public int[] id;
	 public BBQuickFind2(int N){
		id=new int[N];
		for(int i=0;i<N;i++){
			id[i]=i;
		}
	}
	public void union(int b,int a){
		if(connected(b,a)){
			System.out.println(a+" & "+b+" is already connected");
		}
		else for(int i=0;i<id.length;i++){
			if(connected(i,b))
			id[i]=id[a];
		}
	}
	public Boolean connected(int a, int b){
		return id[a]==id[b];
	}
	public static void main(String[]args){

	}

}
