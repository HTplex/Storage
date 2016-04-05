package threads;

/**
 * Created by htplex on 21/7/2015.
 */
public class T02yield {
  public static void main(String[]args){
    Runnable C=new printChar('a',100);
    Runnable N=new printNum(100);
    Thread a=new Thread(C);
    Thread b=new Thread(N);
    a.start();
    b.start();
  }
}
class printNum implements Runnable{
  private int Num;
  printNum(int n){
    this.Num=n;
  }
  public void run(){
    for (int i = 0; i < this.Num; i++) {
      System.out.print(i);
      Thread.yield();
    }
  }
}

class printChar implements Runnable{
  private char CharToPrint;
  private int Times;
  printChar(char c, int t){
   this.CharToPrint=c;
    this.Times=t;
  }
  public void run(){
    for (int i = 0; i < this.Times; i++) {
      System.out.print(this.CharToPrint);
      Thread.yield();
    }
  }
}
