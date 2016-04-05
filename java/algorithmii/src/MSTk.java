import java.util.*;

public class MSTk {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int v = in.nextInt();
    int w = in.nextInt();
    EWG e = new EWG(v, w);
    for (int i = 0; i < w; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      double c = in.nextDouble();
      e.addEdge(new Edge(a-1, b-1, c));
    }
    MSTk ms = new MSTk(e);
    int sum=0;
    for (Edge ed : ms.edges) {
      System.out.println(ed.v + " " + ed.w + " " + ed.distance);
      sum+=ed.distance;
    }
    System.out.println(sum);
    in.close();
  }

  Queue<Edge> edges;
  int size;

  public MSTk(EWG ew) {
    edges = new Queue<Edge>();
    mst(ew);
  }

  public void mst(EWG ew) {
    this.size = 0;
    MinPQ<Edge> temp = new MinPQ<Edge>();
    UF u = new UF(ew.V);
    for (int i = 0; i < ew.E; i++) {
      temp.insert(ew.edges[i]);
    }
    while ((!temp.isEmpty()) && (this.size < ew.V - 1)) {
      Edge e = temp.delMin();
      if (!u.connected(e.either(), e.other(e.either()))) {
        u.union(e.either(), e.other(e.either()));
        edges.enqueue(e);
        this.size++;
      }
    }
  }

  public Iterable<Edge> mstedges() {
    return edges;
  }
}


class Edge implements Comparable<Edge> {
  int v;
  int w;
  double distance;

  public Edge(int v, int w, double distance) {
    this.v = v;
    this.w = w;
    this.distance = distance;
  }

  int either() {
    return v;
  }

  int other(int v) {
    if (v == this.v)
      return w;
    return v;
  }

  public int compareTo(Edge that) {
    double b = this.distance - that.distance;
    return b > 0 ? 1 : b == 0 ? 0 : -1;
  }
}


class EWG {
  int V;
  int E;
  Bag<Edge> adj[];
  Edge[] edges;
  int count;

  public EWG(int v, int e) {
    this.count = 0;
    this.V = v;
    this.E = e;
    adj = (Bag<Edge>[]) new Bag[V];
    for (int i = 0; i < V; i++) {
      this.adj[i] = new Bag<Edge>();
    }
    this.edges = new Edge[E];
  }

  public void addEdge(Edge e) {
    int a = e.either();
    int b = e.other(a);
    this.adj[b].add(e);
    this.adj[a].add(e);
    this.edges[this.count++] = e;
  }

  public Iterable<Edge> adj(int v) {
    return this.adj[v];
  }

}
