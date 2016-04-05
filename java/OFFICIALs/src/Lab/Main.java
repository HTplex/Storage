package Lab;

/**
 * Created by htplex on 4/3/16.
 */
import java.util.Scanner;
public class Main {
  public static void main(String[] args) {
    Scanner cin = new Scanner(System.in);
    int  n = cin.nextInt();
    int[][] a  = new int[n][n];
    int flag=0;
    a[n/2][n/2]=++flag;
    a[n/2][n/2+1]=++flag;
    int x = n/2;
    int y = n/2+1;
    while(flag<n*n) {
      while(x-1>=0&&a[x-1][y]==0&&a[x][y-1]!=0){
        a[x-1][y] = ++flag;
        x--;
      }
      while(y-1>=0&&a[x][y-1]==0&&a[x+1][y]!=0){
        a[x][y-1] = ++flag;
        y--;
      }
      while(x+1<n&&a[x+1][y]==0&&a[x][y+1]!=0){
        a[x+1][y] = ++flag;
        x++;
      }
      while(y+1<n&&a[x][y+1]==0&&a[x-1][y]!=0){
        a[x][y+1] = ++flag;
        y++;
      }
    }
    for(int i=0;i<n;i++) {
      for(int j =0;j<n;j++) {
        if(j!=n-1)
          System.out.print(n*n+1-a[n-1-i][n-1-j]+" ");
        else
          System.out.print(n*n+1-a[n-1-i][n-1-j]);
      }
      System.out.println();
    }
  }
}