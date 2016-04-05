package hiho;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by htplex on 3/14/16.
 */
public class A4 {

  public static void main(String[]args){
    Scanner in=new Scanner(System.in);
    int N=in.nextInt();
    for (int i = 0; i < N; i++) {
      int n=in.nextInt();
      item[] items=new item[n];
      for (int j = 0; j < n; j++) {
        float price=in.nextFloat();
        int desire=in.nextInt();
        items[j]=new item(price, desire);
      }
      Arrays.sort(items);
    }
  }




  static class item implements Comparable<item>{
    int doubledPrice;
    int want;
    public item(float price, int want){
      this.doubledPrice=(int)(2*price);
      this.want=want;

    }
    public int compareTo(item that){
      return that.want-this.want;
    }
  }
}
