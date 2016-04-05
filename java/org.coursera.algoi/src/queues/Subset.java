package queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by htplex on 21/10/2015.
 */
public class Subset {
  public static void main(String[] args)
  {int k = Integer.parseInt(args[0]);
    RandomizedQueue<String> q;q = new
          RandomizedQueue<>();
    while (!StdIn.isEmpty()) {
      q.enqueue(StdIn.readString(
      ));}for (int i = 0; i < k; i++) {
      StdOut.println(q.dequeue());
    }
  }
}
