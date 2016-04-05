package rain2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//改成三段。
//作者   南京工程学院  胡大曙  版权所有
import java.util.ArrayList;



import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class RainBow extends JFrame implements ActionListener{
  public static void main(String[] args)
  {
    RainBow af=new RainBow();



  }
  MyPanel b=null;
  MyFirstPanel x=null;
  JMenuBar m=null;
  JMenu ff=null;
  JMenuItem start=null;
  public RainBow()
  {
    m=new JMenuBar();
    this.setJMenuBar(m);
    ff=new JMenu("rain");
    m.add(ff);
    start=new JMenuItem("start");
    ff.add(start);




    x=new MyFirstPanel();
    Thread ts=new Thread(x);
    ts.start();
    this.add(x);
    start.addActionListener(this);


    this.setTitle("池塘夜降彩色雨");
    this.setSize(600, 700);
    this.setVisible(true);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




  }
  @Override
  public void actionPerformed(ActionEvent arg0) {
    // TODO Auto-generated method stub
    b=new MyPanel();
    Thread t=new Thread(b);
    t.start();
    //这点是最难的
    this.remove(start);
    this.add(b);
    this.addKeyListener(b);
    this.setVisible(true);

  }

}
class MyFirstPanel extends JPanel implements Runnable
{



  public MyFirstPanel()
  {

  }
  public void paint(Graphics g)
  {
    super.paint(g);
    g.fillRect(0, 0, 600, 750);
    Font a=new Font("华文新魏",Font.BOLD,15);
    g.setFont(a);
    g.setColor(Color.WHITE);

    g.drawString("loading...", 150, 300);

  }
  @Override
  public void run() {
    try
    {

    }catch(Exception e)
    {
      e.printStackTrace();
    }
    // TODO Auto-generated method stub
    repaint();
  }

}

class MyPanel extends JPanel implements KeyListener,Runnable
{



  ArrayList<RainDrop> s=new ArrayList<RainDrop>();

  RainDrop hrainbow=null;
  RainDrop hm=null;
  RainDrop ff=new RainDrop();
  int m;
  int f;
  int h=0;
  int k;
  Image image1=null;
  Image image2=null;
  public MyPanel()
  {
    this.xingc();
    image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/Users/htplex/Documents/code/java/water/src/rain2/2.png"));
    image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/Users/htplex/Documents/code/java/water/src/rain2/3.png"));

  }
  public void xingc()
  {

    //
    try
    {

      for(int f=1;f<3;f++)
      {
        for(int j=0;j<10;j++)


        {
          k=(int)(Math.random()*3)+3;
          //m=(int)(Math.random()*5)+2;
          m=2;
          //看来还真控制不了每行的大小，当所有行都出来，那么它就跟最后一行一样

          for (int i = 0; i < 50; i++) {

            hrainbow = new RainDrop(10 * ( i+ 1), (j+1)*10);

            s.add(hrainbow);

            //	System.out.println(s);

            Thread tt = new Thread(hrainbow);
            tt.start();
          }
        }
//	}


      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    finally
    {

    }

  }

  public void paint(Graphics g)
  {
    //让闪电的位置随机出现，那么需要设置随机数，把这个数字尽量弄大些，
    //这样就出现了不出现闪电的概率，随机出现怎样保持闪动的效果！
    super.paint(g);
    h++;
    g.fillRect(0, 0, 600, 700);
    g.setColor(Color.red);
    g.drawImage(image1,0,0,600,650,this);
//		g.draw3DRect(0,  100, 100, 100,false);
//		if(h%7==0)
//		{
//		g.drawLine(80, 60, 30, 160);
//		g.drawLine(30, 160, 50,160);
//		g.drawLine(50, 160, 20, 220);
//		}
//		if(h%7==1)
//		{
//			g.drawLine(280, 60, 230, 160);
//			g.drawLine(230, 160, 250,160);
//			g.drawLine(250, 160, 220, 220);
//		}
//		if(h%7==2)
//		{
//			g.drawLine(480, 60, 430, 160);
//			g.drawLine(430, 160, 450,160);
//			g.drawLine(450, 160, 420, 220);
//		}
//		
    g.drawImage(image2,0,0,600,500,this);



    for(int i=0;i<s.size();i++)
    {
      RainDrop a=s.get(i);


      if(a.getY()<=80&&a.getY()>=0)
      {
        this.drawraindrop(a.getX(),a.getY(),g,a.color);

      }
      if(a.getY()>=580&&a.getY()<=590)
      {
        this.drawqunquqn(a.getX(),a.getY(),g,a.color);
      }
      if(a.getY()>=590&&a.getY()<600)
      {
        this.drawqunquqna(a.getX(),a.getY(),g,a.color);
      }
      if(a.getY()>600)
      {
        a.setX(a.getX());
        a.setY(20);

        //s.remove(a);
      }
//			if(a.getY()>=700)
//			{
//			
//				s.remove(a);
//			}
      if(a.getY()>=80&&a.getY()<580)

      {


        a.setX((a.getX()));

        a.setY(a.getY()+1);


        this.drawraindrop(a.getX(),a.getY(),g,a.color);


      }
      //this.drawqunquqn(a.getX(),a.getY(),g,a.color);
    }

  }












  public void drawqunquqn(int x, int y, Graphics g, int color) {

    switch(color)
    {
      case 0:
        g.setColor(Color.red);
        break;
      case 1:
        g.setColor(Color.orange);
        break;
      case 2:
        g.setColor(Color.yellow);
        break;
      case 3:
        g.setColor(Color.green);
        break;
      case 4:
        g.setColor(Color.cyan);
        break;
      case 5:
        g.setColor(Color.blue);
        break;
      case 6:
        g.setColor(Color.green);
        break;
    }
    g.drawOval(x, y, 5, 5);
    // TODO Auto-generated method stub

  }
  public void drawqunquqna(int x, int y, Graphics g, int color) {

    switch(color)
    {
      case 0:
        g.setColor(Color.red);
        break;
      case 1:
        g.setColor(Color.orange);
        break;
      case 2:
        g.setColor(Color.yellow);
        break;
      case 3:
        g.setColor(Color.green);
        break;
      case 4:
        g.setColor(Color.cyan);
        break;
      case 5:
        g.setColor(Color.blue);
        break;
      case 6:
        g.setColor(Color.green);
        break;
    }
    g.drawOval(x, y, 10, 10);
    // TODO Auto-generated method stub

  }
  public void drawraindrop(int x,int y,Graphics g,int color)
  {
    switch(color)
    {
      case 0:
        g.setColor(Color.red);
        break;
      case 1:
        g.setColor(Color.orange);
        break;
      case 2:
        g.setColor(Color.yellow);
        break;
      case 3:
        g.setColor(Color.green);
        break;
      case 4:
        g.setColor(Color.cyan);
        break;
      case 5:
        g.setColor(Color.blue);
        break;
      case 6:
        g.setColor(Color.green);
        break;
    }
    g.drawLine(x, y, x, y+8);

  }
  public void keyPressed(KeyEvent arg0) {
    if(arg0.getKeyCode()==KeyEvent.VK_W);
    {
      //没监听到！！
      System.out.println(3);
    }

    // TODO Auto-generated method stub
    this.repaint();
  }

  @Override
  public void keyReleased(KeyEvent arg0) {
    // TODO Auto-generated method stub

  }
  @Override
  public void keyTyped(KeyEvent arg0) {
    // TODO Auto-generated method stub

  }
  @Override
  public void run() {
    while(true)
    {
      try

      {





      }catch(Exception e)
      {
        e.printStackTrace();
      }
      // TODO Auto-generated method stub

      this.repaint();
    }
  }




}
class RainDrop implements Runnable
{

  int kk=0;
  public int getKk() {
    return kk;
  }
  public void setKk(int kk) {
    this.kk = kk;
  }
  int s;
  int n=1;
  int gg;
  public int getN() {
    return n;
  }
  public void setN(int n) {
    this.n = n;
  }
  boolean islive=true;
  public boolean isIslive() {
    return islive;
  }
  public void setIslive(boolean islive) {
    this.islive = islive;
  }
  int color=(int)(Math.random()*7);
  public int getColor() {
    return color;
  }
  public void setColor(int color) {
    this.color = color;
  }
  int x;
  int y;


  public int getSpeed() {
    return speed;
  }
  public void setSpeed(int speed) {
    this.speed = speed;
  }
  int speed=5;
  public int getX() {
    return x;
  }
  public void setX(int x) {
    this.x = x;
  }
  public int getY() {
    return y;
  }
  public void setY(int y) {
    this.y = y;
  }
  public RainDrop(int x,int y)
  {
    this.x=x;
    this.y=y;

  }
  public RainDrop() {

    // TODO Auto-generated constructor stub
  }
  //	public void drawraindrop(int x,int y,Graphics g,int color)
//	{
//		switch(color)
//		{
//		case 0:
//			g.setColor(Color.red);
//			break;
//		case 1:
//			g.setColor(Color.orange);
//			break;
//		case 2:
//			g.setColor(Color.yellow);
//			break;
//		case 3:
//			g.setColor(Color.green);
//			break;
//		case 4:
//			g.setColor(Color.cyan);
//			break;
//		case 5:
//			g.setColor(Color.blue);
//			break;
//		case 6:
//			g.setColor(Color.green);
//			break;
//		}
//		g.fillRoundRect(x, y, 8,8, 8, 8);
//		
//	}
//	
  @Override
  public void run() {
    while(true)
    {
      speed=(int)(Math.random()*5);
      try
      {
        if(n==1)
        {
          Thread.sleep(100);
          y=y+speed;
          s=s+1;
          if(s>80)
          {
            //	n=2;
          }
        }	else
        {


          gg=(int)(Math.random()*4);
          Thread.sleep(100);
          y=y-speed;
          x=x-8;
          s=s+1;
          if(s>40)
          {
            n=1;
          }
        }
      }catch(Exception e)
      {
        e.printStackTrace();
      }

      // TODO Auto-generated method stub
    }
  }

}



