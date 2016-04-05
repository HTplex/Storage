package Lab;

/**
 * Created by htplex on 3/21/16.
 */
public class Choose {
  public static void main(String[]args){
    Choose c=new Choose(3,6);
    c.next(0);
  }
  private int[] nums;
  private int bound;
  public Choose(int a, int b){
    this.bound=b;
    this.nums=new int[a];
    for (int i = 0; i < a; i++) {
      this.nums[i]=i;
    }
  }
  public void next(int c){
//    int n=this.nums.length;
//    if(c!=n-1) {
//      if (this.nums[n - 1] == this.bound) {
//        if (c < n - 1) this.nums[c + 1] = this.nums[c] + 1;
//        return;
//      }
//
//    }

    if(c>this.nums.length-1) return;
    while(this.nums[c]<this.bound) {
      //this.nums[c]++;
      next(c + 1);
      this.nums[c]++;

    }
    this.nums[c]=this.nums[c-1]+1;
    //print();
  }
  public void print(){
      for (int i1 : this.nums) {
        System.out.print(i1 + " ");
      }
      System.out.println();
    }
}






