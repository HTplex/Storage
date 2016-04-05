package water;

/**
 * Created by htplex on 20/7/2015.
 */

import java.awt.*;


public class Rain { // class Rain is the class for one raindrop
  public int rain_x;
  public int rain_y;

  public Rain() {
  }

  public Rain(int _x, int _y) {// setup method x,y is for position
    this.rain_x = _x;
    this.rain_y = _y;
  }


  public void setx(int _x) {
    this.rain_x = _x;
  } //set x

  public void sety(int _y) {
    this.rain_y = _y;
  } //set y

  public int getX() {
    return rain_x;
  }  // get the value of x

  public int getY() {
    return rain_y;
  } // get the value of y

  public void draw(Graphics g) {
    if (rain_y <= 100) {
      g.setColor(Color.RED);
    } else if (rain_y > 100 && rain_y <= 200) {
      g.setColor(Color.YELLOW);
    } else if (rain_y > 200 && rain_y <= 300) {
      g.setColor(Color.GREEN);
    } else if (rain_y > 300 && rain_y <= 400) {
      g.setColor(Color.GRAY);
    } else {
      g.setColor(Color.WHITE);
    }
    g.drawLine(rain_x, rain_y, rain_x - 2, rain_y + 6);
  }


}
