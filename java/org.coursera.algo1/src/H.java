import java.util.Arrays;
import java.util.Scanner;


/**
 * Created by htplex on 28/11/2015.
 */
public class H {
  public static void main(String[]args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    for (int i = 0; i < n; i++) {
      int n1 = in.nextInt();
      int num = in.nextInt();
      int[] a = new int[n1];
      String s = in.nextLine();
     String[] k = s.split(" ");
      System.out.println(s);
      for (String s1 :k) {
     //   System.out.println(s1);
      }

      for (int i1 = 0; i1 < n1; i1++) {
      //  a[i1] = Integer.parseInt(k[i1]);
      }
      Arrays.sort(a);
      long sum = 0;
      for (int j = 0; j < 2 * num; j++) {
        sum += a[j];
      }
      System.out.println(sum);
    }
  }
  }
