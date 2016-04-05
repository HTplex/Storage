/////With HUGE BUG//////
import java.util.Scanner;
public class MSTp {
	public static void main(String[]args){
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		int m=in.nextInt();
		WEG w=new WEG(n,m);
		for(int i=0;i<n;i++){
			int a=in.nextInt();
			int b=in.nextInt();
			double d=in.nextDouble();
			w.addEdge(new Edgep(a,b,d));
		}
		MSTp p=new MSTp(w);
		for(Edgep e:p.edges)
			System.out.println(e.v+" "+e.w+" "+e.distance);
	}
	Queue<Edgep> edges;
	boolean[] marked;
//	
//	public MSTp(WEG g){
//	this.edges=new Queue<Edgep>();
//	this.marked=new boolean[g.v];
//	for(int i=0;i<g.v;i++){
//		this.marked[i]=false;
//	}
//	mst(g,0);
//	}
	public Iterable<Edgep> edges(){
		return this.edges;
	}
//	public void mst(WEG g, int s){
//		this.marked[s]=true;
//		for(Edgep e:g.adj[s]){
//			if(!marked[e.other(s)]){
//				edges.enqueue(e);
//				mst(g,e.other(s));
//				
//			}
//		}
//	}
	MinPQ<Edgep> str;
	public MSTp(WEG we){
		this.marked=new boolean[we.v];
		for(int i=0;i<we.v;i++) this.marked[i]=false;
		str=new MinPQ<Edgep>();
		edges=new Queue<Edgep>();
		mst(we,0);
	}
	public void mst(WEG g, int s){
		this.marked[s]=true;
		for(Edgep e:g.adj[s]){
			this.str.insert(e);
		}
		while(!this.str.isEmpty()){
			Edgep min=this.str.delMin();
			if((!this.marked[min.v])||(!this.marked[min.w])){
				this.edges.enqueue(min);
				mst(g,this.marked[min.v]?min.w:min.v);
			}
		}
	}
	
}
class WEG{
	Bag<Edgep>[] adj;
	int v;
	int e;
	public WEG(int v, int e){
		this.v=v;
		this.e=e;
		adj=(Bag<Edgep>[])(new Bag[this.v]);
		for(int i=0;i<v;i++){
			this.adj[i]=new Bag<Edgep>();
		}
	}
	public void addEdge(Edgep e){
		this.adj[e.v].add(e);
		this.adj[e.w].add(e);
	}
	public Iterable<Edgep> adj(int v){
		return this.adj[v];
	}
}
class Edgep implements Comparable<Edgep>
{
	int v;
	int w;
	double distance;
	public Edgep(int v, int w, double distance){
		this.v=v; 
		this.w=w;
		this.distance=distance;
	}
	public int other(int m){
		if(this.v==m) return w;
		if(this.w==m) return v;
		return -1;
	}
	public int compareTo(Edgep that){
		double c=this.distance-that.distance;
		if(c>0) return 1;
		else if(c==0) return 0;
		else return -1;			
	}
}