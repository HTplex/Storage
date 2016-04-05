import java.util.Scanner;

public class Lab {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int w = in.nextInt();
    EdgeWeightedGraph G = new EdgeWeightedGraph(w);
    int e = in.nextInt();
    for (int i = 0; i < e; i++) {
      int from = in.nextInt();
      int to = in.nextInt();
      int wei = in.nextInt();
      G.addEdge(new Edge(from-1, to-1, wei));
    }
    
    PrimMST a = new PrimMST(G);
    System.out.println(a.weight());
  }
}
