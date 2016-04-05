package pAssignments;
import java.util.Scanner;

import sun.security.util.Length;
public class Q3FloydWarshall {
  public static void main(String[]args){
    Scanner in=new Scanner(System.in);
    int v=in.nextInt();
    int [][][] mins=new int[v][v][2];
    int[][] cc=new int[v][v];
    int[][] length=new int[v][v];
    int mm=984984984;
    int e=in.nextInt();
    for(int i=0;i<e;i++){
      int a=in.nextInt();
      int b=in.nextInt();
      int c=in.nextInt();
      cc[a-1][b-1]=c;
    }
    for(int i=0;i<v;i++){
      for(int i1=0;i1<v;i1++){
        if(i==i1) mins[i][i1][0]=0;
        else if(cc[i][i1]!=0) {
          mins[i][i1][0]=cc[i][i1];
          length[i][i1]=1;
        }
        else mins[i][i1][0]=99999;
      }
    }

    for(int k=0;k<v;k++){
      for(int i=0;i<v;i++){
        for(int j=0;j<v;j++){
        if(mins[i][j][0]>mins[i][k][0]+mins[k][j][0]){
          mins[i][j][1]=mins[i][k][0]+mins[k][j][0];
         // 
          
        }
        else mins[i][j][1]=mins[i][j][0];
        if(mins[i][j][1]>1000000)
        System.out.println(mins[i][j][1]);
        mm=min(mm, mins[i][j][0]);
        
//          mins[i][j]=min(mins[i][j], mins[i][k]+mins[k][j]);
//          mm=min(mm, mins[i][j]);
        }
      }
      for(int i=0;i<v;i++){
        for(int j=0;j<v;j++){
          mins[i][j][0]=mins[i][j][1];
        }
      }
    }
//    for (int[] ls : mins) {
//      for (int l : ls) {
//        System.out.print(l+"\t");
//      }
//      System.out.println();
//    } 
//    
//    for (int[] ls : length) {
//      for (int l : ls) {
//        System.out.print(l+"\t");
//      }
//      System.out.println();
//    } 
    //System.out.println(length[tempa][tempb]);
    
    System.out.println(mm);
    for(int i=0;i<v;i++){
        if(mins[i][i][0]<0)
          System.out.println("OUT!");
    }
  }
  public static int min(int a, int b){
    return a<b?a:b;
  }
}
