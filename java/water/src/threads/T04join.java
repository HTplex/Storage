package threads;

/**
 * Created by htplex on 21/7/2015.
 */

/**
 * Created by htplex on 21/7/2015.
 */
public class T04join {
  public static void main(String[]args){
    Runnable C=new printChar3('a',100);
    Runnable N=new printNum3(100);
    Thread a=new Thread(C);
    Thread b=new Thread(N);
    a.start();
    b.start();
  }
}
class printNum3 implements Runnable{
  private int Num;
  printNum3(int n){
    this.Num=n;
  }
  public void run(){
    Thread newThread=new Thread(new printChar3('@',50));
    newThread.start();
    try {
      for (int i = 0; i < this.Num; i++) {
        System.out.print(i);
        if(i==50) newThread.join();
      }
    }
    catch (InterruptedException ex){}
  }
}

class printChar3 implements Runnable{
  private char CharToPrint;
  private int Times;
  printChar3(char c, int t){
    this.CharToPrint=c;
    this.Times=t;
  }
  public void run(){

    for (int i = 0; i < this.Times; i++) {
      System.out.print(this.CharToPrint);
      //Thread.yield();
    }
  }
}

