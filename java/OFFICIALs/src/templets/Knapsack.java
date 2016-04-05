package templets;

import java.util.Scanner;

/**
 * Created by htplex on 4/2/16.
 */
public class Knapsack {
  public static void main(String[]args){
    Scanner in=new Scanner(System.in);
    int n=in.nextInt(); //number of items
    int[][] data=new int[n][2]; //storage item attributes;
    int h=in.nextInt();
    int[][] dp=new int[n][h];
    for (int i = 0; i < n; i++) {
      data[i][0]=in.nextInt();
      data[i][1]=in.nextInt();
    }
  }
  
}
