package main;

import java.util.Scanner;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Calculatepos {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    BigInteger twoBigInteger = new BigInteger("2");
    BigInteger tenBigInteger = new BigInteger("10");
    System.out.println(twoBigInteger.pow(128));
    System.out.println(tenBigInteger.pow(30));
    System.out.println(tenBigInteger.pow(36));
    System.out.println(tenBigInteger.pow(42));
  }
}
