package hiho;

import java.util.Scanner;

/**
 * Created by htplex on 4/2/16.
 */
public class W091 {
  public static void main(String[]args){
    int n,m,k;
    Scanner in=new Scanner(System.in);
    n=in.nextInt();
    m=in.nextInt();
    k=in.nextInt();
    int[][] data=new int[n][3];
    for (int i = 0; i < n; i++) {
      data[i][0]=in.nextInt();
      data[i][1]=in.nextInt();
      data[i][2]=in.nextInt();
    }

  }

}
