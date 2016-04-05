

public class HelloGraph {
	public static void main(String[]args){
		HelloGraph HG=new HelloGraph(6);
		HG.addEdge(0, 2);
		HG.addEdge(3, 4);
		HG.addEdge(0, 4);
		HG.addEdge(1, 5);
		DFS s=new DFS(HG,0);
		System.out.println(s.connected(2));
		System.out.println(s.connected(5));
		for(int i:s.pathTo(3)) System.out.print(i);
		System.out.println();
		BFS b=new BFS(HG,0);
		System.out.println(b.distTo[3]);
		for(int i:s.pathTo(3)) System.out.print(i);
		System.out.println();
		CON c=new CON(HG);
		System.out.println(c.connected(0, 3));
		System.out.println(c.connected(0, 5));
	}
	
	
	
	final int V;
	Bag<Integer>[] adj;
	public HelloGraph(int V){
		this.V=V;
		adj=(new Bag[V]);
		for(int i=0;i<V;i++){
			adj[i]=new Bag<Integer>();
		}
	}
	public void addEdge(Integer s, Integer t){
		adj[s].add(t);
		adj[t].add(s);
	}
	public Iterable<Integer> adj(int v){
		return adj[v];
	}			
}

class DFS{
	Integer s;
	boolean[] marked;
	private int[] edgeTo;
	DFS(HelloGraph G, Integer ss){
		this.s=ss;
		this.marked=new boolean[G.V];
		this.edgeTo=new int[G.V];
		for(int i=0;i<G.V;i++){
			this.marked[i]=false;
			//this.edgeTo[i]=-1;
		}
		dfs(G, s);
	}
	public void dfs(HelloGraph G, Integer org){
		this.marked[org]=true;
		for(int i:G.adj[org]){
			if(!marked[i]){
				dfs(G, i);
				edgeTo[i]=org;
			}
		}
	}
	public boolean connected(Integer t){
		return marked[t];
	}
	public Iterable<Integer> pathTo(int V){
		if(!connected(V)) return null;
		Stack<Integer> to=new Stack<Integer>();
		int i=V;
		while(i!=s){
			to.push(i);
			i=edgeTo[i];
		}
		to.push(s);
		return to;
	}
}
class BFS{
	Queue<Integer> Q;
	int s;
	Integer[] edgeTo;
	Integer[] distTo;
	boolean[] marked;
	BFS(HelloGraph G, Integer s){
		this.Q=new Queue<Integer>();
		this.s=s;
		marked=new boolean[G.V];
		distTo=new Integer[G.V];
		edgeTo=new Integer[G.V];
		for(int i=0;i<G.V;i++){
			marked[i]=false;
			distTo[i]=-1;
		}
		distTo[s]=0;
		bfs(G,s);
	}
	public void bfs(HelloGraph G, Integer s){
		this.Q=new Queue<Integer>();
		this.Q.enqueue(s);
		marked[s]=true;
		while(!Q.isEmpty()){
			int g=Q.dequeue();
			for(int i:G.adj[g]){
				if(!marked[i]){

					Q.enqueue(i);
					this.marked[i]=true;
					this.edgeTo[i]=g;
					this.distTo[i]=this.distTo[g]+1;
				}
			}
		}
	}
	public boolean connected(Integer t){
		return marked[t];
	}
	public Iterable<Integer> pathTo(int V){
		if(!connected(V)) return null;
		Stack<Integer> to=new Stack<Integer>();
		int i=this.s;
		while(i!=s){
			to.push(i);
			i=edgeTo[i];
		}
		to.push(s);
		return to;
	}
}
class CON{
	Integer[] cc;
	int n;
	boolean marked[];
	CON(HelloGraph G){
		this.n=G.V;
		this.cc=new Integer[G.V];
		this.marked=new boolean[n];
		for(int i=0;i<n;i++){
			marked[i]=false;
		}
		con(G);
		
	}
	public void con(HelloGraph G){
		cc=new Integer[G.V];
		
		int count=0;
		for(int i=0;i<n;i++){
			if(marked[i]==false){
				con(G,i,count);
				count++;
			}
		}
	}
	public void con(HelloGraph G, Integer s, int i){
		this.marked[s]=true;
		this.cc[s]=i;
		for(int it:G.adj(s)){
			if(!marked[it]){
				marked[it]=true;
				this.cc[it]=i;
				con(G,it,i);
			}
		}
	}
	public boolean connected(Integer i, Integer j){
		return this.cc[i]==this.cc[j];
	}
}