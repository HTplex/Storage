package Lab;

/**
 * Created by htplex on 3/21/16.
 */
public class time {
  public static void main(String[]args){
    long b;
    long a=System.nanoTime();
    long s=0;
    for (int i = 0; i < 10000000; i++) {
      //s+=i;
    }
    b=System.nanoTime()-a;
    System.out.println(b);
  }
}
