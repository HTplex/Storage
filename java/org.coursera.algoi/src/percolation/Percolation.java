package percolation;
/**
 * Created by htplex on 21/9/2015.
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;



public class Percolation {

  private boolean[] opened;
  private int size;
  private WeightedQuickUnionUF UF;
  //private WeightedQuickUnionUF backwash;

  public Percolation(int N){
    if(N<=0) throw new IllegalArgumentException("");
    this.size=N;
    this.opened=new boolean[N*N];
    this.UF=new WeightedQuickUnionUF(N*N+N);
    //this.backwash=new WeightedQuickUnionUF(N*N);
    for (int i = 0; i < N-1; i++) {
      this.UF.union(i,i+1);
      //this.backwash.union(i,i+1);
      this.UF.union(N*N-i-1,N*N-i-2);
    }
  }
               // create N-by-N grid, with all sites blocked
  public void open(int i, int j){
    i--;
    j--;
    if(i<0||j<0||i>this.size-1||j>this.size-1) throw new IndexOutOfBoundsException("");
    this.opened[this.size*i+j]=true;
    if(i>0&&this.opened[this.size*(i-1)+j])   {
      this.UF.union(this.size*i+j,this.size*(i-1)+j);
      //this.backwash.union(this.size*i+j,this.size*(i-1)+j);
    }
    if(i<this.size-1&&this.opened[this.size*(i+1)+j]){
      this.UF.union(this.size*i+j,this.size*(i+1)+j);
     // this.backwash.union(this.size * i + j, this.size * (i + 1) + j);
    }
    if(j>0&&this.opened[this.size*i+(j-1)]){
      this.UF.union(this.size*i+j,this.size*i+(j-1));
     // this.backwash.union(this.size*i+j,this.size*i+(j-1));
    }
    if(j<this.size-1&&this.opened[this.size*i+(j+1)]){
      this.UF.union(this.size*i+j,this.size*i+(j+1));
     // this.backwash.union(this.size*i+j,this.size*i+(j+1));
    }
    //if(i=this.size-2){ }

  }          // open site (row i, column j) if it is not open already
  public boolean isOpen(int i, int j){
    i--;
    j--;
    if(i<0||j<0||i>this.size-1||j>this.size-1) throw new IndexOutOfBoundsException("");
    return this.opened[this.size*i+j];
  }     // is site (row i, column j) open?

  public boolean isFull(int i, int j){
    i--;
    j--;
    if(i<0||j<0||i>this.size-1||j>this.size-1) throw new IndexOutOfBoundsException("");
    return this.UF.connected(this.size * i + j, 0)&&this.isOpen(i+1,j+1);
  }     // is site (row i, column j) full?
  public boolean percolates(){
    if(this.size==1) return this.isOpen(1, 1);
    return this.UF.connected(0,this.opened.length-1);
  }             // does the system percolate?
  // public void print(){
  //   for (int i = 0; i < this.size; i++) {
  //     for (int j = 0; j < this.size; j++) {
  //       if(!this.isOpen(i+1,j+1))
  //         System.out.print('X');
  //       else if(this.isFull(i+1,j+1))
  //         System.out.print('*');
  //       else System.out.print('_');
  //     }
  //     System.out.println();
  //   }
  // }
  public static void main(String[] args){


  }  // test client (optional)
}