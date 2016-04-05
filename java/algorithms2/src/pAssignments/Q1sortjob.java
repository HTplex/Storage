package pAssignments;

import java.util.Arrays;
import java.util.Comparator;
// this problem is about greedy algorithms, sort jobs with different
// length and weight with length-weight;
import java.util.Scanner;

public class Q1sortjob {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    jobs[] j = new jobs[n];
    for (int i = 0; i < n; i++) {
      int a = in.nextInt();
      int b = in.nextInt();
      j[i] = new jobs(a, b);
    }
    Arrays.sort(j, jobs.BY_WEIGHT);
    Arrays.sort(j, jobs.BY_RATIO);
    for (int i = 0; i < j.length; i++)
      j[i].show();
    System.out.println(CompTime(j));
  }

  public static long CompTime(jobs[] j) {
    long accu = 0;
    long sum = 0;
    for (int i = 0; i < j.length; i++) {
      accu += j[i].length;
      sum += accu * j[i].weight;
    }
    return sum;
  }
}


class jobs {
  public static final Comparator<jobs> BY_RATIO = new ByRatio();
  public static final Comparator<jobs> BY_DIFF = new ByDiff();
  public static final Comparator<jobs> BY_WEIGHT = new ByWeight();
  int weight;
  int length;
  double ratio;
  int diff;

  jobs(int a, int b) {
    this.weight = a;
    this.length = b;
    this.ratio = (double) (a) / b;
    this.diff = a - b;
  }

  public void show() {
    System.out.println(this.length + "\t" + this.weight + "\t" + this.diff + "\t" + this.ratio);
  }

  private static class ByRatio implements Comparator<jobs> {
    public int compare(jobs a, jobs b) {
      return a.ratio < b.ratio ? 1 : a.ratio == b.ratio ? 0 : -1;
    }
  }

  private static class ByWeight implements Comparator<jobs> {
    public int compare(jobs a, jobs b) {
      return a.weight < b.weight ? 1 : a.weight == b.weight ? 0 : -1;
    }
  }

  private static class ByDiff implements Comparator<jobs> {
    public int compare(jobs a, jobs b) {
      return a.diff < b.diff ? 1 : a.diff == b.diff ? 0 : -1;
    }
  }
}
