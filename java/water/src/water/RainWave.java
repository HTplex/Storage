package water;

/**
 * Created by htplex on 20/7/2015.
 */

import java.awt.*;

public class RainWave {             //水圈类
  public int wave_weight;           //水圈宽
  public int wave_height;           //水圈高
  Graphics2D gg;                    //图片
  private int wave_x;             //水圈横坐标
  private int wave_y;             //水圈纵坐标

  public RainWave(int wave_x, int wave_y) {   //定义方法
    this.wave_x = wave_x;
    this.wave_y = wave_y;
    this.wave_height = 0;
    this.wave_weight = 0;
  }

  public void draw(Graphics g) {              //画水圈
    if (wave_height <= 10) {
      gg = (Graphics2D) g;
      g.setColor(new Color(255, 255, 255));       //随机颜色
    }
    if (wave_height > 10 && wave_height <= 20) {
      gg = (Graphics2D) g;
      gg.setStroke(new BasicStroke(2.0f));
      gg.setColor(new Color(230, 230, 230));

    }
    if (wave_height > 20 && wave_height <= 30) {    //每个深度不同颜色
      gg = (Graphics2D) g;
      gg.setStroke(new BasicStroke(2.5f));
      gg.setColor(new Color(180, 180, 180));
    }
    if (wave_height > 30) {
      gg = (Graphics2D) g;
      gg.setStroke(new BasicStroke(3.0f));
      gg.setColor(new Color(100, 100, 100));
    }
    gg.drawOval(wave_x, wave_y, wave_height, wave_weight);    //画水圈
    this.waveMove();
  }

  public void waveMove() {          //水圈放大
    this.wave_height += 4;
    this.wave_weight += 1;
  }


}
