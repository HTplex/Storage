package templets;

/**
 * Created by htplex on 3/7/16.
 */
public class FastMatrixMultiply {
  final int MOD = 10001;
  private long[][] acc;
  private long[][] one;
  private int time;

  public FastMatrixMultiply(long[][] ori, int times) {
    this.one = ori.clone();
    this.acc = ori.clone();
    this.time = times;
    this.Fmm();
  }

  public static void main(String[] args) {
    long[][] a = new long[2][2];
    a[0][0] = 1;
    a[0][1] = 1;
    a[1][0] = 1;
    a[1][1] = 0;

    FastMatrixMultiply fmm = new FastMatrixMultiply(a, 5);
    long[][] c = fmm.result().clone();
    for (long[] ints : c) {
      for (long i : ints) {
        System.out.print(i);
      }
      System.out.println();
    }


  }

  public long[][] Multiply(long[][] a, long[][] b) {
    long[][] outPut = new long[a.length][b[0].length];
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < b[0].length; j++) {
        for (int k = 0; k < b.length; k++) {
          outPut[i][j] += a[i][k] * b[k][j];
        }
      }
    }
    return outPut;
  }

  private void plus() {
    this.acc = Multiply(this.acc, this.one);
  }

  private void duplicate() {
    this.acc = Multiply(this.acc, this.acc);
  }

  public void Fmm() {
    String s = Integer.toBinaryString(this.time);
    for (int i = 1; i < s.length(); i++) {
      char c = s.charAt(i);
      this.duplicate();
      if (c == '1') this.plus();
    }
    //1000 1001 10 11

  }

  public long[][] result() {
    return this.acc;
  }
}
