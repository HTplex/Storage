package main;
import java.util.Scanner;
public class AssemblyTest1 {
  public static void main(String[]args){
    Scanner in=new Scanner(System.in);
    while(in.hasNext()){
      int i=in.nextInt();
      String s=in.next();
      System.out.println(BasicTools.HextoInt(s));
    }
  }
}
