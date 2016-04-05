package Lab;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by htplex on 4/2/16.
 */
public class Brackets_Sequence_02 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String s = in.nextLine();
    if (s.length() == 0) {
      System.out.println();
    } else {
      int length = s.length();
      String[][] fs = new String[length][length];
      int[][] dp = new int[length][length];
      for (int i = 0; i < length; i++) {
        Arrays.fill(fs[i], "");
      }
      for (int i = 0; i < length; i++) {
        dp[i][i] = 2;
        if (s.charAt(i) == '[' || s.charAt(i) == ']') fs[i][i] = "[]";
        else fs[i][i] = "()";
      }
      int min;
      for (int i = 1; i < length; i++) {
        for (int j = 0; j + i < length; j++) {
          min = Integer.MAX_VALUE;
          int q = i + j;//(j,j+i)
          if ((s.charAt(j) == '[' && s.charAt(q) == ']') || (s.charAt(j) == '(' && s.charAt(q) == ')')) {
            dp[j][q] = dp[j + 1][q - 1] + 2;
            min = dp[j][q];
            fs[j][q] = s.charAt(j) + fs[j + 1][q - 1] + s.charAt(q);
          }
          for (int k = j; k < q; k++) {
            if (min > dp[j][k] + dp[k + 1][q]) {
              min = dp[j][k] + dp[k + 1][q];
              fs[j][q] = fs[j][k] + fs[k + 1][q];
            }
          }
          dp[j][q] = min;
        }
      }


    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        System.out.print(dp[i][j]+" ");
      }
      System.out.println();
    }
    System.out.println();

    for (int i = 0; i <length ; i++) {
      for (int j = 0; j < length; j++) {
        System.out.print(fs[i][j]+"\t\t");
      }
      System.out.println();
    }
      System.out.println(fs[0][length - 1]);
    }
  }
}
