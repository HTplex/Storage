package main;
import java.util.Scanner;
public class StringToHEX {
  public static void main(String[]args){
    Scanner in=new Scanner(System.in);
    String string=in.nextLine();
    for(int i=0;i<string.length();i++){

      String charnum=Integer.toHexString((int)(string.charAt(i)));
      System.out.print(charnum);
    }
  }
}
