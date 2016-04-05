package hiho;

/**
 * Created by htplex on 3/13/16.
 */

import java.util.Arrays;
import java.util.Scanner;


/**
 * Created by htplex on 3/13/16.
 */
public class A2 {


  public static void main(String[]args) {
    Scanner in = new Scanner(System.in);



    int N = in.nextInt();
    for (int r = 0; r < N; r++) {
      int[][] desires = new int[10][50];
      int[] curser = new int[10];

      int n = in.nextInt();
      for (int i = 0; i < n; i++) {
        float price = in.nextFloat();
        int des = in.nextInt();
        int mod = ((int) (price * 2)) % 10;
        desires[mod][curser[mod]++] = des;
      }

      for (int i = 0; i < 10; i++) {
        Arrays.sort(desires[i], 0, curser[i]);
      }
      int max = 0;

        if (curser[0] != 0 && max < desires[0][0]) {
          max = desires[0][0];
        }

      for (int i = 1; i < 10; i++) {
        if (curser[i] != 0 && curser[10 - i] != 0) {
          int mm = desires[i][0] + desires[10 - i][0];
          if (max < mm) {
            max = mm;
          }
        }
      }
      if (curser[0] > 1) {
        int mm = desires[0][0] + desires[0][1];
        if (max < mm) {
          max = mm;
        }
      }
      if (curser[0] > 2) {
        int mm = desires[0][0] + desires[0][1] + desires[0][2];
        if (max < mm) {
          max = mm;
        }
      }

      for (int i = 0; i < 10; i++) {
        for (int j = 1; j < 10; j++) {
          int[] cc = new int[10];
          cc[i]++;
          cc[j]++;
          int k = i + j > 10 ? 20 - i - j : 10 - i - j;
          cc[k]++;
          if (cc[i] <= curser[i] && cc[j] <= curser[j] && cc[k] <= curser[k]) {
            int[] cur = new int[10];
            int mm = 0;
            int pp = 0;
            mm += desires[i][cur[i]++];
            mm += desires[j][cur[j]];
            mm += desires[k][cur[k]];
            if (max < mm) {
              max = mm;
            }
          }
        }
      }


      System.out.println(max);


//    for (int i = 0; i < 10; i++) {
//      for (int i1=0;i1<curser[i];i1++) {
//        System.out.print(items[i][i1].desire+" ");
//      }
//      System.out.println();
//    }

    }
  }

}
