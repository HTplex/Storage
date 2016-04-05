package water;

/**
 * Created by htplex on 20/7/2015.
 */

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Rains implements Runnable {             //下雨类
  private static int number;
  LinkedList<Rain> rain = new LinkedList<Rain>();       //雨滴链表
  LinkedList<RainWave> wave = new LinkedList<RainWave>();   //水圈链表
  Random random = new Random();       //随机参数
  Thread t;           //进程

  public Rains() {
    t = new Thread(this);
    t.start();
  }

  public void creat() {         //创建雨滴

    for (int i = 0; i < 2; i++) {//每个进程两个
      rain.add(new Rain(200 + random.nextInt(800 - 200 + 1), 0));//随机雨滴位置
    }
  }

  public void draw1(Graphics g) { //画水滴

    for (int i = 0; i < rain.size(); i++) {

      rain.get(i).draw(g);
    }
    this.move();
    if (wave.size() > 0) {
      for (int i = 0; i < wave.size(); i++) {
        wave.get(i).draw(g);
        this.waveDisapper();       //到位置随机结束
      }
    }
  }

  public void move() {        //水滴更新

    for (int i = 0; i < rain.size(); i++) {
      rain.get(i).rain_x -= 3;//3        //风速
      rain.get(i).rain_y += 10;//10       //下落速度
      this.disapper();                   //上一个结束
    }
  }

  private void disapper() {           //消失方法
    number = 350 + random.nextInt(500 - 300 + 1);
    for (int i = 0; i < rain.size(); i++) {
      //System.out.println(number) ;
      if (rain.get(i).rain_y == number) {
        wave.add(new RainWave(rain.get(i).rain_x, rain.get(i).rain_y));//加入水圈队列
        rain.remove(i);      //从水滴队列中删除

      }
    }
  }

  private void waveDisapper() {     //水圈随机大小消失
    for (int i = 0; i < wave.size(); i++) {
      if (wave.get(i).wave_height == 60 + random.nextInt(21)) {
        wave.remove(i);
      }
    }

  }


  @Override
  public void run() {       //进程开始
    while (true) {
      try {
        this.creat();
        Thread.sleep(50);//休息时间 雨的大小
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }


}






