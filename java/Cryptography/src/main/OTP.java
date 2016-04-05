package main;
import java.util.Scanner;


import main.BasicTools;
public class OTP {
  public static void main(String[] args){
    Scanner inScanner=new Scanner(System.in);
    String string=inScanner.nextLine();
    String key=HexKeyGen(string.length());
    System.out.println(key);
    System.out.println(encrypt(string, key));
    System.out.println(decrypt("0b070b060008000908030e0600", "5f6f627520617329666a6d632e"));
    inScanner.close();
  }
  static String HexKeyGen(int n){
    StringBuilder sb=new StringBuilder();
    for(int i=0;i<n;i++){
      int temp=(int)(Math.random()*16);
      sb.append(BasicTools.IntToHex(temp));
    }
    return sb.toString();
  }
  static String encrypt(String string, String HexKey){
    String Hex=BasicTools.StrtoHex(string);
    return BasicTools.HexXor(HexKey, Hex);
  }
  static String decrypt(String cipher, String HexKey){
    String Hex=BasicTools.HexXor(cipher, HexKey);
    return BasicTools.HexToStr(Hex);
  }
}
