package water;

/**
 * Created by htplex on 20/7/2015.
 */

import javax.swing.*;
import java.awt.*;


public class MyJFrame extends JFrame implements Runnable {  //定义Frame
  MyJPanel panel;     //添加背景
  MyApplet applet;    //添加声音

  public MyJFrame(String s) {     //初始化
    super(s);
    Thread t = new Thread(this);  //进程开始
    t.start();
    applet = new MyApplet();      //声音开始
    this.add(applet);
    this.setDefaultCloseOperation
            (JFrame.EXIT_ON_CLOSE);
    this.setBounds(100, 50, 900, 600);    //画界限
    panel = new MyJPanel(900, 600);
    this.add(panel, BorderLayout.CENTER);     //处于窗口中心
    this.setVisible(true);        //可见
  }

  public static void main(String[] args) {    //主程序开始
    new MyJFrame("池塘夜降彩色雨");

  }

  private void launch() {               //开始下雨进程
    applet.init();
  }

  private void launch1() {            //开始闪电进程
    applet.start();
  }

  @Override
  public void run() {               //进程开始
    while (true) {
      try {
        Thread.sleep(6000);         //六秒后打雷
        this.launch();
        new MyJPanel().flag = true; //闪电可用
        Thread.sleep(4000);         //十秒后闪电
        this.launch1();

      } catch (InterruptedException e) {  //抛出错误防止闪退
        e.printStackTrace();
      }
    }
  }
}