package water;

/**
 * Created by htplex on 20/7/2015.
 */
//MyApplet.java

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.applet.Applet;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class MyApplet extends Applet {//音频类

  public void init() {//雷声雨风声

    try {
      InputStream in = new
              FileInputStream("D:\\Audio\\gao.wav");//雷声导入
      InputStream in2 = new
              FileInputStream("D:\\Audio\\feng.wav");//风声导入
      AudioStream as = new AudioStream
              (in);
      AudioStream as2 = new

              AudioStream(in2);
      AudioPlayer.player.start(as);      //雷声开始
      AudioPlayer.player.start(as2);   //风声开始
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void start() {
    try {
      InputStream in1 = new
              FileInputStream("D:\\Audio\\sheji.wav");//闪电声音

      AudioStream as1 = new
              AudioStream(in1);
      AudioPlayer.player.start(as1);       //闪电声音开始
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
//MyJFrame.java










