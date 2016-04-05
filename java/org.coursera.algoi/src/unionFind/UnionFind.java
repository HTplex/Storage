package unionFind;
import java.util.Scanner;
/**
 * Created by htplex on 16/9/2015.
 */


public class UnionFind {
  public static void main(String[]args){
    UnionFind UF=new UnionFind(10);
    UF.interactiveConnect3();
    UF.showIndex();
  }

  private int numberOfPoints;
  private int[] index;
  UnionFind(int N){
    this.numberOfPoints=N;
    this.index=new int[N];
    this.weight=new int[N];
    for (int i = 0; i <N; i++) {
      this.index[i] = i;
      this.weight[i]=1;
    }
  }
  public void showIndex(){
    for (int i = 0; i <this.numberOfPoints ; i++) {
      System.out.print(i + "\t");
    }
    System.out.println();
    for (int i = 0; i < this.numberOfPoints; i++) {
      System.out.print(this.index[i] + "\t");
    }
    System.out.println();
  }

  public void multiConnect(int[] a, int[] b){
    if(a.length!=b.length){
      System.out.println("a & b's length not match!");
    }
    for (int i = 0; i <a.length; i++) {
      connect1(a[i], b[i]);
    }
  }
  public void interactiveConnect1(){
    Scanner in=new Scanner(System.in);
    int i=in.nextInt();
    for (int j = 0; j < i; j++) {
      int a=in.nextInt();
      int b=in.nextInt();
      if(isConnected1(a,b)) System.out.println(a+" & "+b+" is already connected");
      else connect1(a,b);
      this.showIndex();
    }

  }
  public void connect1(int a, int b){
    int ia=index[a];
      for (int i = 0; i < this.numberOfPoints; i++) {
        if (index[i] == ia) {
          index[i] = index[b];
        }
      }
  }
  public boolean isConnected1(int a, int b){
    return this.index[a]==this.index[b];
  }


  public int root(int n ){
    while(this.index[n]!=n)
      n=this.index[n];
    return n;
  }
  public boolean isConnected2(int a, int b){
    return root(a)==root(b);
  }
  public void connect2(int a, int b){
    this.index[root(b)]=root(a);
  }
  public void interactiveConnect2(){
    Scanner in=new Scanner(System.in);
    int i=in.nextInt();
    for (int j = 0; j < i; j++) {
      int a=in.nextInt();
      int b=in.nextInt();
      if(isConnected2(a, b)) System.out.println(a+" & "+b+" is already connected");
      else connect2(a, b);
    }
  }

  private int[] weight;
  public void connect3(int a, int b){
    int ra=root(a);
    int rb=root(b);
    if(this.weight[ra]>=this.weight[rb]){
      this.index[rb]=ra;
      this.weight[ra]+=this.weight[rb];
    }
    else{
      this.index[ra]=rb;
      this.weight[rb]+=this.weight[ra];
    }
  }
  public void interactiveConnect3(){
    Scanner in=new Scanner(System.in);
    int i=in.nextInt();
    for (int j = 0; j < i; j++) {
      int a=in.nextInt();
      int b=in.nextInt();
      if(isConnected2(a, b)) System.out.println(a+" & "+b+" is already connected");
      else connect3(a, b);
      this.showIndex();
    }

  }

  public void connect4(int a, int b){
    int ra=root2(a);
    int rb=root2(b);
    if(this.weight[a]>this.weight[b]){
      this.index[rb]=ra;
      this.weight[ra]+=this.weight[rb];
    }else{
      this.index[ra]=rb;
      this.weight[rb]+=ra;
    }
  }
  public int root2(int n){
    while(this.index[n]!=n){
      this.index[n]=this.index[this.index[n]];
      n=this.index[n];
    }
    return n;
  }

}

