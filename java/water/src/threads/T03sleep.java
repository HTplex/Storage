package threads;


/**
 * Created by htplex on 21/7/2015.
 */
public class T03sleep {
  public static void main(String[] args) {
    Runnable C = new printChar2('a', 100);
    Runnable N = new printNum2(100);
    Thread a = new Thread(C);
    Thread b = new Thread(N);
    a.start();
    b.start();
  }
}

class printNum2 implements Runnable {
  private int Num;

  printNum2(int n) {
    this.Num = n;
  }

  public void run() {
    try {
      for (int i = 0; i < this.Num; i++) {
        System.out.print(i);
        Thread.sleep(1);
      }
    } catch (InterruptedException ex) {
    }
  }
}

class printChar2 implements Runnable {
  private char CharToPrint;
  private int Times;

  printChar2(char c, int t) {
    this.CharToPrint = c;
    this.Times = t;
  }

  public void run() {
    try {
      for (int i = 0; i < this.Times; i++) {
        System.out.print(this.CharToPrint);
        Thread.sleep(10);
      }
    } catch (InterruptedException ex) {
    }
  }
}
