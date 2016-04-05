package threads;

/**
 * Created by htplex on 21/7/2015.
 */
public class T01helloThreads {
  public static void main(String[]args){
    Runnable printA=new PrintChar('a',1000);
    Runnable printB=new PrintChar('b', 1000);
    Runnable printC=new PrintNum(1000);

    Thread thread1=new Thread(printA);
    Thread thread2=new Thread(printB);
    Thread thread3=new Thread(printC);

    thread1.start();
    thread2.start();
    thread3.start();
  }
}

class PrintChar implements Runnable{
  private char CharToPrint;
  private int Times;
  PrintChar(char c, int t){
    this.CharToPrint=c;
    this.Times=t;
  }
  public void run(){
    for (int i = 0; i <this.Times; i++) {
      System.out.print(this.CharToPrint);
    }
  }

}

class PrintNum implements Runnable{
  private int Times;
  PrintNum(int t){
    this.Times=t;
  }
  public void run(){
    for (int i = 0; i < this.Times; i++) {
      System.out.print(i+" ");
    }
  }
}
