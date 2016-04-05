package Lab;
import java.util.Scanner;

/**
 * Created by htplex on 3/28/16.
 */
public class aa {
  public static void main(String[]args){
   Scanner in=new Scanner(System.in);
    while(in.hasNext()){
      int m;
      int n=in.nextInt();
      int b=in.nextInt();
      if(n%2==1){
        if(b<(n/2)+1){
          m=b+1;
        }
        else{
          m=b-1;
        }
      }
      else{
        if(b<=(int)(n/2)){
          m=b+1;
        }
        else{
          m=b-1;
        }

      }
      System.out.println(m);
    }
  }
}
