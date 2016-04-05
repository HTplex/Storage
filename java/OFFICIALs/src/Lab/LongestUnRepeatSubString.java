package Lab;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by htplex on 3/26/16.
 */
public class LongestUnRepeatSubString {
  public static void main(String[]args){
    Scanner in=new Scanner(System.in);
    String s=in.next();
    int start=0;
    int maxStart=0;
    int maxEnd=0;
    int[] currentPosion=new int[255];
    Arrays.fill(currentPosion, -1);
    for (int i = 0; i < s.length(); i++) {
      if(currentPosion[(int)(s.charAt(i))]!=-1){
        int tstart=currentPosion[(int)(s.charAt(i))]+1;

        for (int j = start; j <= i; j++) {
          currentPosion[(int)(s.charAt(j))]=-1;
        }
        start=tstart;
      }
      currentPosion[(int)(s.charAt(i))]=i;
      if(maxEnd-maxStart<i-start){
        maxEnd=i;
        maxStart=start;

      }
      System.out.println(s.substring(maxStart,maxEnd+1));
    }
    System.out.println(maxStart+" "+maxEnd);
    System.out.println(s.substring(maxStart,maxEnd+1));

  }

}
