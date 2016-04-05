
import java.util.Scanner;

/**
 * Created by htplex on 5/11/2015.
 */

public class Test {
  public static void main(String[]args){
    Scanner in=new Scanner(System.in);
    String s=in.next();
    System.out.println(ToolBox.StrToBin(s));
    System.out.println(ToolBox.StrtoHex(s));
  }
}
