package main;
import java.util.Scanner;
public class HEXtoString {
  public static void main(String[]args){
    Scanner in=new Scanner(System.in);
    String inputString=in.next();
    for(int i=0;i<=inputString.length()-2;i+=2){
      Integer charnum=Integer.parseInt(inputString.substring(i,i+2),16);
      System.out.print((char)(charnum.intValue()));
    }
    
    in.close();
  }
}  
