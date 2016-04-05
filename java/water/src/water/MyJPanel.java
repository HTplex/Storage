package water;

/**
 * Created by htplex on 20/7/2015.
 */

import javax.swing.*;
import java.awt.*;

public class MyJPanel extends JPanel implements Runnable {     //初始化背景
  public static boolean flag = false;            //暂时不能闪电
  public int height;               //设置高
  public int weight;               //设置宽
  Rains rains;           //下雨
  Image image;           //背景
  Image img[] = new Image[5];          //荷花

  public MyJPanel(int height, int weight) {       //初始化窗口
    this.height = height;
    this.weight = weight;
    rains = new Rains();
    Thread t = new Thread(this);
    t.start();

    MediaTracker tracker = new MediaTracker(this);     //荷花
    img[0] = Toolkit.getDefaultToolkit().createImage("/Users/htplex/Desktop/3.png");
    img[1] = Toolkit.getDefaultToolkit().createImage("/Users/htplex/Desktop/3.png");
    img[2] = Toolkit.getDefaultToolkit().createImage("/Users/htplex/Desktop/3.png");
    img[3] = Toolkit.getDefaultToolkit().createImage("/Users/htplex/Desktop/3.png");
    img[4] = Toolkit.getDefaultToolkit().createImage("/Users/htplex/Desktop/3.png");
    for (int i = 0; i < img.length; i++) {
      tracker.addImage(img[i], 0);
    }
    try {
      tracker.waitForAll();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

  public MyJPanel() {

  }

  public void paint(Graphics g) {       //画图

    Color c = g.getColor();
    g.setColor(new Color(50, 50, 50));
    g.fillRect(0, 0, height, weight - 250);      //夜空
    g.setColor(Color.blue);
    g.fillRect(0, weight - 300, height, 250);  //水面
    if (this.flag == true) {
      rains.draw1(g);
      g.setColor(new Color(100, 100, 100));
      g.fillRect(0, 0, height, weight);      //闪电
      g.setColor(Color.WHITE);
      g.fillOval(700, 20, 10, 10);

      g.drawLine(700, 21, 300, 400);        //闪电
      g.drawLine(700, 21, 350, 300);
    } else {
      rains.draw1(g);                //画
    }
    this.flag = false;
    g.setColor(c);


  }


  public void run() {                     //开始背景线程
    while (true) {
      repaint();

      try {
        Thread.sleep(50);//150           //1/50秒更新一次
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }


  }

  public void update(Graphics g) {          //更新
    if (image == null) {
      image = this.createImage(900, 600);
    }
    Graphics gc = image.getGraphics();
    paint(gc);
    g.drawImage(image, 0, 0, null);

  }

}



