
public class Digraph {
	int V;
	int E;
	Bag<Integer>[] adj;
	public Digraph(int V){
		adj=(Bag<Integer>[])new Bag[V];
		this.V=V;
		this.E=0;
		for(int i=0;i<V;i++){
			this.adj[i]=new Bag<Integer>();
		}
	}
	public void addEdge(int v, int w){
		this.adj[v].add(w);
		this.E++;
	}
	public void addEdge(int[] v, int[] w){
		for(int i=0;i<v.length;i++){
			this.addEdge(v[i], w[i]);
		}
		this.E+=v.length;
	}
	public void reverse(){
		
	}
	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder("");
		for(int i=0;i<this.V;i++)
			for(int e:adj[i])
				sb.append(i+"->"+e+"\n");
	return sb.toString();	
	}
	public Iterable<Integer> adj(int v){
		return this.adj[v];
	}
	public static void main(String[]args){
		Digraph d=new Digraph(8);
		int[] v={0,0,0,1,2,3,5,6,6,7};
		int[] w={1,6,7,2,7,4,4,5,7,4};
		d.addEdge(v, w);
		System.out.println(d.toString());
		dDFS f=new dDFS(d,0);
		System.out.println(f.showpath(2));
		DFTS ff=new DFTS(d);
		for(int i:ff.reversePost())
		System.out.print(i);
	}
}
class dDFS{
	boolean marked[];
	Integer s;
	Integer[] edgeTo;
	public dDFS(Digraph D, int s){
		this.s=s;
		this.marked=new boolean[D.V];
		for(int i=0;i<D.V;i++)
			marked[i]=false;
		this.edgeTo=new Integer[D.V];
		dfs(D,s);
	}
	public void dfs(Digraph D, int s){
		this.marked[s]=true;
		for(int i:D.adj[s]){
			if(!marked[i]){
				dfs(D,i);
				this.edgeTo[i]=s;
			}
		}
	}
	public boolean connected(int v){
		return this.marked[v];
	}
	public String showpath(int v){
		if(!connected(v)) return "not connected";
		else{
			StringBuilder sb=new StringBuilder("");
			int it=v;
			while(it!=this.s){
						sb.append(it+"<-");
				it=this.edgeTo[it];
			}
			sb.append(s);
			return sb.toString();
		}
	}
}
class dBFS{
	Queue<Integer> Q;
	int s;
	Integer[] edgeTo;
	Integer[] distTo;
	boolean[] marked;
	dBFS(Digraph D, Integer s){
		this.Q=null;
		this.s=D.V;
		this.edgeTo=new Integer[D.V];
		this.distTo=new Integer[D.V];
		this.marked=new boolean[D.V];
		for(int i=0;i<D.V;i++){
			this.marked[i]=false;
			this.distTo[i]=-1;
		}
		this.distTo[s]=0;
		bfs(D,s);
	}
	public void bfs(Digraph D, Integer s){
		Q.enqueue(s);
		this.marked[s]=true;
		while(!Q.isEmpty()){
			int i=Q.dequeue();
			for(int j:D.adj[i]){
				if(!marked[j]){
					marked[j]=true;
					distTo[j]=distTo[i]+1;
					edgeTo[j]=i;
					Q.enqueue(j);
				}
			}
		}
	}
	public void multibfs(Digraph D, Integer s1,Integer s2, Integer s3){
		Q.enqueue(s1);
		Q.enqueue(s2);
		Q.enqueue(s3);
		this.marked[s1]=true;
		this.marked[s2]=true;
		this.marked[s3]=true;
		while(!Q.isEmpty()){
			int i=Q.dequeue();
			for(int j:D.adj[i]){
				if(!marked[j]){
					marked[j]=true;
					distTo[j]=distTo[i]+1;
					edgeTo[j]=i;
					Q.enqueue(j);
				}
			}
		}
	}
	public boolean connected(int that){
		return this.marked[that];
	}
	public String showpath(int v){
		if(!connected(v)) return "not connected";
		else{
			StringBuilder sb=new StringBuilder("");
			int it=v;
			while(it!=this.s){
						sb.append(it+"<-");
				it=this.edgeTo[it];
			}
			sb.append(s);
			return sb.toString();
		}
	}
}
class DFTS{
	boolean marked[];
	Integer mark;
	Stack<Integer> reversePost;
	public DFTS(Digraph G){
		this.mark=0;
		this.reversePost=new Stack<Integer>();
		this.marked=new boolean[G.V];
		for(int i=0;i<G.V;i++)
			this.marked[i]=false;
		dfts(G);
	}
	public void dfs(Digraph G, Integer i){
		this.marked[i]=true;
			for(int c:G.adj[i]){
				if(!this.marked[c]){
					dfs(G,c);
				}
			}
			reversePost.push(i);
	}
	public void dfts(Digraph G){
		for(int i=0;i<G.V;i++){
			if(!this.marked[i]){
				dfs(G,i);
			}
		}
	}
	public Iterable<Integer> reversePost(){
		Stack<Integer> t=new Stack<Integer>();
		t.push(-1);
		for(int i:this.reversePost){
		if(i==-1) return new Stack<Integer>();
		}
		return this.reversePost;
	}
	public boolean connected(Integer i){
		return marked[i];
	}
}
