package percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


/**
 * Created by htplex on 22/9/2015.
 */
public class PercolationStats {
  private int N;
  private int T;
  private double[] Rs;


  public PercolationStats(int N, int T){
    Percolation PC;
    if(N<=0||T<=0) throw new java.lang.IllegalArgumentException("");
    this.N=N;
    this.T=T;
    this.Rs=new double[T];
    int p=0;
    for (int i = 0; i < T; i++) {
      PC=new Percolation(N);
      double count=0;
      while(!PC.percolates()){
       // int a=(int)(Math.random()*N+1);
       // int b=(int)(Math.random()*N+1);
        int a=StdRandom.uniform(1,N+1);
        int b=StdRandom.uniform(1,N+1);
       // System.out.println(a);
        if(!PC.isOpen(a,b)){
          PC.open(a,b);
          count++;
        }
      }
      Rs[p++]+=count/(this.N*this.N);
    }

  }     // perform T independent experiments on an N-by-N grid
  public double mean(){
    return StdStats.mean(this.Rs);

  }                      // sample mean of percolation threshold
  public double stddev(){
    return StdStats.stddev(this.Rs);
  }                    // sample standard deviation of percolation threshold
  public double confidenceLo(){
    return this.mean()-(1.96*stddev())/(Math.sqrt(this.T));
  }              // low  endpoint of 95% confidence interval
  public double confidenceHi(){
    return this.mean()+(1.96*stddev())/(Math.sqrt(this.T));
  }              // high endpoint of 95% confidence interval

  public static void main(String[] args){
    PercolationStats pc=new PercolationStats(2,10000);

    System.out.println(pc.mean());
    System.out.println(pc.stddev());
    System.out.println(pc.confidenceLo());
    System.out.println(pc.confidenceHi());
  }    // test client (described below)
}
