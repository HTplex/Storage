import java.util.Scanner;

public class GrammerAnalysis{
  private static int num = 0;
  private static char [] input = null;

  public static boolean E(){
    if (input[num] == 'e'){
      num++;
      if (B() && input[num] == 'a' ){
        num++;
        if (A()){
          return true;
        }
      }
    }
    return false;
  }

  public static boolean B(){
    if (input[num] == 'd'){
      num++;
      if (E()){
        if (input[num] == 'd'){
          num++;
          return true;
        }
      }
    }

    else if (input[num] == 'a'){
      num++;
      if (C()){
        return true;
      }
    }

    return false;
  }

  public static boolean C() {

    if (input[num] == 'e'){
      num++;
      return true;
    }

    else if (input[num] == 'd'){
      num++;
      if (C()){
        return true;
      }
    }

    return false;
  }

  public static boolean A(){

    if (input[num] == 'a'){
      num++;
      return true;
    }

    else if (input[num] == 'b'){
      num++;
      if (A()){
        if (input[num] == 'c'){
          num++;
          if (B()){
            return true;
          }
        }
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String inputString = in.next();
    input = inputString.toCharArray();
    if (E()){
      System.out.println("accepted!");
      System.out.println();
      System.out.println("2133605 张浩天");
    }else {
      System.out.println("rejected!");
      System.out.println();
      System.out.println("2133605 张浩天");

    }
  }
}
