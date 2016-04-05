package Lab;

import java.util.Scanner;

/**
 * Created by htplex on 4/2/16.
 */
public class Brackets_Sequence{
  public static void main(String[]args){
    Scanner in=new Scanner(System.in);
    String s=in.next();
    int length=s.length();
    int[][] dp=new int[length][length];
    for (int i = 0; i < length; i++) {
      dp[i][i]=2;
    }
    int min;
    for (int i = 1; i < length; i++) {
      for (int j = 0; j+i < length; j++) {
        min=Integer.MAX_VALUE;
        int q=i+j;//(j,j+i)
        if((s.charAt(j)=='['&&s.charAt(q)==']')||(s.charAt(j)=='('&&s.charAt(q)==')')){
          dp[j][q]=dp[j+1][q-1]+2;
          min=dp[j][q];
        }
        for (int k = j; k < q; k++) {
          min=min(min,dp[j][k]+dp[k+1][q]);
        }
        dp[j][q]=min;
      }
    }


    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length; j++) {
        System.out.print(dp[i][j]+" ");
      }
      System.out.println();
    }
  }
  public static int min(int a, int b){
    return a<b?a:b;
  }


}
