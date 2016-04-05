import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by htplex on 28/11/2015.
 */
public class E {
  public static void main(String[]args){

    TreeMap<Integer, Integer> a =new TreeMap<>();
    Scanner in=new Scanner(System.in);
    while(in.hasNext()){
      a.clear();
      int n=in.nextInt();
      int ex=0;
      int[] exnums=new int[n];
       Arrays.fill(exnums,-1);
      int[] nums=new int[n];
      boolean[] gos=new boolean[n];
      int one=0;
    for (int i = 0; i <n; i++) {
      String s=in.next();
      int p=in.nextInt();
      nums[i]=p;
      if(s.equals("+")){
        gos[i]=true;
        a.put(p,p);
    }
      else {
        gos[i]=false;
        if(!a.containsKey(p)) {
          exnums[one++]=p;
        }
        a.remove(p);
      }
    }
    a.clear();
      int sum=0;
      for(int o=0;o<exnums.length&& exnums[o]>0;o++){
        a.put(exnums[o],exnums[o]);

      }
       sum=a.size();
      for (int i = 0; i <n; i++) {

        int p=nums[i];

        if(gos[i]){
          a.put(p,p);
        }
        else {
          a.remove(p);
        }
        sum=max(sum,a.size());
      }
    System.out.println(sum);

    }
  }
  static int max(int a, int b){
    return a>b?a:b;
  }
}
